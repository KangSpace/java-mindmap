package org.kangspace.common.classloader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 网络class加载器
 *       可指定加载类位置,指定加载类
 *       指定加载jar位置(使用sun.misc.Launcher$AppClassLoader加载)
 *       解压jar文件
 * @date 2017/6/22 11:30
 */
public class NetworkClassLoader extends ClassLoader{

    private NetworkClassLoader() {
        this(null,null,null);
    }

    public NetworkClassLoader(String url) {
        this(null, null,url);
    }
    public NetworkClassLoader(String protocol,String url) {
        this(null, protocol,url);
    }

    public NetworkClassLoader(ClassLoader parent, String protocol,String url) {
        super((parent == null ? ClassLoader.getSystemClassLoader() : parent));
        if(url!=null){
            url = url.replace("\\","/");
            if(url.lastIndexOf("/") != (url.length()-1))
                url += "/";
        }
        if(protocol == null)
            protocol = "file:///";
        this.protocol = protocol;
        this.url = url;
    }
    /**
     * 获取路径,协议+url
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 10:18
     */
    public String getPath(){
        return this.protocol + this.url;
    }

    /**
     * <pre>
     * 加载器查找并加载类
     * 实现自定义ClassLoader必须重写该方法,ClassLoader默认实现抛出一个 ClassNotFoundException。
     * </pre>
     * @param name 类名,如:com._20dot.common.A
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 11:32
     * @return
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData;
        try {
            classData = loadClassData(name);
        } catch (IOException e) {
            throw new ClassNotFoundException(name,e);
        }
        return defineClass(name,classData,0,classData.length);
    }

    /**
     * 加载类文件
     * @param name 文件名称,含路径
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 11:38
     * @return
     */
    protected byte[] loadClassData(String name) throws IOException {
        String path = name.replace('.', '/').concat(".class");
        path = getPath()+path;
        URL classUrl = new URL(path);
        if(classUrl == null)
            throw new FileNotFoundException();
        InputStream is = classUrl.openStream();
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] b1024 = new byte[1024];
            int len;
            while ((len = is.read(b1024)) != -1) {
                bos.write(b1024, 0, len);
            }
        } finally {
            is.close();
            if(bos != null)
                bos.close();
        }
        return bos.toByteArray();
    }

    /**
     * <pre>
     * 查找资源
     * 实现查找资源必须实现该方法,ClassLoader默认返回null
     * </pre>
     * @param name
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 11:40
     * @return
     */
    @Override
    public URL findResource(String name) {
        //TODO 未实现
        return super.findResource(name);
    }

    /**
     * <pre>
     * 查找资源,并返回枚举
     * 实现查找资源必须实现该方法,ClassLoader默认返回空枚举
     * </pre>
     * @param
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 11:40
     * @return
     */
    @Override
    public Enumeration<URL> findResources(String name) throws IOException {
        //TODO 未实现
        return super.findResources(name);
    }

    /**
     * 加载jar包,本地文件
     * 使用sun.misc.Launcher$AppClassLoader加载
     * @param isContainsSubDir 是否包含子目录中的jar
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 16:26
     * @return
     */
    public void loadJars(Boolean isContainsSubDir) throws NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException {
        ClassLoader parent = this.getParent();
        if (parent != null && parent instanceof URLClassLoader) {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
            add.setAccessible(true);
            File pathDir = new File(this.url);
            List<File> files = new ArrayList<File>();
            findJars(isContainsSubDir,pathDir,files);
            for (File file : files) {
                add.invoke(parent, new Object[]{file.toURI().toURL()});
            }
        }
    }

    /**
     * 查找file下的所有jar
     * @param isContainsSubDir 是否包含子目录中的jar
     * @param file 文件或路径
     * @param files 将找到的文件保存到该集合中
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 9:59
     */
    static void findJars(Boolean isContainsSubDir, File file, List<File> files) {
        if(file.exists()) {
            if (isContainsSubDir) {
                findJars(file, files);
            } else {
                if (file.isDirectory()) {
                    File[] tmps = file.listFiles();
                    for (File temp : tmps) {
                        if (temp.getAbsolutePath().endsWith(".jar"))
                            files.add(temp);
                    }
                } else {
                    if (file.getAbsolutePath().endsWith(".jar"))
                        files.add(file);
                }
            }
        }
    }

    /**
     * 解压jar包
     * @param jarFile_
     * @param unJarPath
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 11:30
     * @return
     */
    public static void unJar(File jarFile_,String unJarPath) throws IOException {
        if(jarFile_ == null || !jarFile_. exists() || !jarFile_.isFile() || !jarFile_.getName().endsWith(".jar"))
            throw new FileNotFoundException(unJarPath+"not exists or not jar file");
        JarFile jarFile = new JarFile(jarFile_);
        File unJarPathFile = new File(unJarPath);
        if(unJarPathFile.exists() && !unJarPathFile.isDirectory())
            throw new IllegalArgumentException(unJarPath+"was not directory ");
        if(!unJarPathFile.exists())
            unJarPathFile.mkdirs();
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
        byte[] bytes = new byte[1024];
        while(jarEntrys.hasMoreElements()){
            ZipEntry entryTemp = jarEntrys.nextElement();
            File desTemp = new File(unJarPathFile.getAbsoluteFile() + File.separator + entryTemp.getName());
            if(entryTemp.isDirectory()){//jar条目是空目录
                if(!desTemp.exists())
                    desTemp.mkdirs();
            }else{    //jar条目是文件
                //因为manifest的Entry是"META-INF/MANIFEST.MF",写出会报"FileNotFoundException"
                File desTempParent = desTemp.getParentFile();
                if(!desTempParent.exists())
                    desTempParent.mkdirs();
                BufferedInputStream in = null;
                BufferedOutputStream out = null;
                try {
                    in = new BufferedInputStream(jarFile.getInputStream(entryTemp));
                    out = new BufferedOutputStream(new FileOutputStream(desTemp));
                    int len = in.read(bytes, 0, bytes.length);
                    while(len != -1){
                        out.write(bytes, 0, len);
                        len = in.read(bytes, 0, bytes.length);
                    }
                } catch (IOException e) {
                    throw e;
                } finally {
                    if (in != null)
                        in.close();
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                }
            }
        }
        jarFile.close();
    }

    /**
     * 查找file下的所有jar
     * @param file 文件或路径
     * @param files 将找到的文件保存到该集合中
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 16:45
     */
    private static void findJars(File file, List<File> files) {
        if(file.exists()) {
            if (file.isDirectory()) {
                File[] tmps = file.listFiles();
                for (File tmp : tmps) {
                    findJars(tmp, files);
                }
            } else {
                if (file.getAbsolutePath().endsWith(".jar"))
                    files.add(file);
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * URL路径字符串,不含协议
     */
    private String url;
    /**
     * 协议,默认为file:///
     */
    private String protocol;
}
