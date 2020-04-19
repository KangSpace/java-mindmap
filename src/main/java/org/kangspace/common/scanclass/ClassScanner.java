package org.kangspace.common.scanclass;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author kango2gler@gmail.com
 * @desc class(文件)扫描类, 含jar中的类(文件)
 * @date 2017/6/28 10:03
 */
public class ClassScanner {
    /**
     * class文件后缀
     */
    public static final String CLASS_FILE_SUFFIX = ".class";
    /**
     * META-INF/ jar entry name
     */
    public static final String JAR_META_INF_ENTRY_NAME = "META-INF/";
    /**
     * jre lib path
     */
    public static final String JRE_LIB_PATH = "/lib";
    /**
     * 需要扫描的包名
     */
    private String packageName;
    /**
     * 扫描到的文件信息
     */
    private ArrayList<ClassInfo> classInfos;
    /**
     * 非jar,file协议的文件
     */
    private ArrayList<ClassInfo> otherClassInfos;

    /**
     * 扫描包下的所有文件
     * @Author kango2gler@gmail.com
     * @Date 2017/6/28 10:19
     * @return
     */
    public ArrayList<ClassInfo> scanClasses() throws IOException {
        if (this.packageName == null || this.packageName.trim().length() == 0)
            throw new NullPointerException("packageName is null");
        initClassInfos();
        initOtherClassInfos();
        String pkg = this.packageName.replace(".", "/");
        java.util.Enumeration<java.net.URL> urls = this.getClass().getClassLoader().getResources(pkg);
        while (urls != null && urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String fileName = url.getFile();
            if(url.getProtocol().equals("file")){//文件夹
                findClasses(url.getPath());
            } else if (url.getProtocol().equals("jar")) {//jar
                findJarClasses(url,pkg);
            } else {
                this.otherClassInfos.add(new ClassInfo(fileName,url.getPath(),url.getProtocol()));
            }
        }
        return this.classInfos;
    }

    /**
     * 查找路径下的所有文件,不含匿名内部类(${n}.class)
     * @param filePath
     * @Author kango2gler@gmail.com
     * @Date 2017/6/28 11:12
     * @return
     */
    private void findClasses(String filePath){

        File[] files = new File(filePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return ((pathname.isFile() && pathname.getName().endsWith(CLASS_FILE_SUFFIX)) || pathname.isDirectory());
            }
        });
        for (File file : files) {
            if(file.isDirectory()) {
                findClasses(file.getPath());
            }else{
                addClassInfo(new ClassInfo(getClassName(file.getPath()), file.getPath(),Protocol.FILE.toString()));
            }
        }
    }
    /**
     * 获取jar中packageName下的所有类,不含匿名内部类(${n}.class)
     * @param
     * @Author kango2gler@gmail.com
     * @Date 2017/6/29 9:30
     * @return
     */
    public void findJarClasses(URL url,String packageName) throws IOException {
        //System.getProperty("java.home")
        JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarUrlConnection.getJarFile();
        java.util.Enumeration<JarEntry> jarEntries = jarFile.entries();
        while(jarEntries.hasMoreElements()){
            JarEntry entry = jarEntries.nextElement();
            if(!entry.isDirectory() && !JAR_META_INF_ENTRY_NAME.equals(entry.getName())
                    && entry.getName().startsWith(packageName)
                    && entry.getName().endsWith(CLASS_FILE_SUFFIX)) {
                String name = entry.getName();
                addClassInfo(new ClassInfo(getClassName(name), entry.toString(),Protocol.JAR.toString(),jarFile.getName()));
            }
        }
    }
    /**
     * 通过classPath获取类名
     * @param classPath
     * @Author kango2gler@gmail.com
     * @Date 2017/6/28 17:50
     * @return
     */
    private String getClassName(String classPath){
        String classesPath = this.getClass().getClassLoader().getResource("").getPath().substring(1);
        classPath = classPath.replace("\\", "/");
        if(classPath.startsWith(classesPath))
            classPath = classPath.substring(classesPath.length());
        return classPath.substring(0, classPath.length() - CLASS_FILE_SUFFIX.length()).replace("/", ".");
    }

    /**
     * 扫描存在Annotaion的Class
     * @param annotationClass
     * @Author kango2gler@gmail.com
     * @Date 2017/6/28 18:07
     * @return
     */
    public List<ClassInfo> findClassByAnnotation(Class<?> annotationClass) throws IOException {
        if(this.classInfos == null)
            scanClasses();
        List<ClassInfo> annClass = new ArrayList<ClassInfo>();
        for (ClassInfo classInfo : classInfos){
            try {
                //去除jre包的扫描
                if(checkWasJreJarFile(classInfo))
                    continue;
                Class clazz = Class.forName(classInfo.getName());
                if (checkHadAnnotation(clazz, annotationClass))
                    annClass.add(classInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return annClass;
    }

    /**
     * 检测文件是否为jre下的jar包文件
     * @param classInfo
     * @Author kango2gler@gmail.com
     * @Date 2017/6/29 9:48
     * @return
     */
    public boolean checkWasJreJarFile(ClassInfo classInfo){
        if(Protocol.JAR.toString().equals(classInfo.getProtocol())) {
            String jreLibPath = (System.getProperty("java.home") + JRE_LIB_PATH).replace("\\","/");
            if (classInfo.getJarName().replace("\\","/").startsWith(jreLibPath))
                return true;
        }
        return false;
    }

    /**
     * 检查类是否有相关注解
     * @param clazz 目标类
     * @param annotationClass 目标注解
     * @Author kango2gler@gmail.com
     * @Date 2017/6/28 18:17
     * @return
     */
    public boolean checkHadAnnotation(Class clazz,Class<?> annotationClass){
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation an : annotations) {
            if (annotationClass.isAssignableFrom(an.getClass()))
                return true;
        }
        return false;
    }

    private void addClassInfo(ClassInfo classInfo){
        //去除匿名内部类的添加
        if(!classInfo.getName().matches(".*\\$\\d$"))
            this.classInfos.add(classInfo);
    }

    private void initClassInfos(){
        this.classInfos = new ArrayList<ClassInfo>();
    }

    private void initOtherClassInfos(){
        this.otherClassInfos = new ArrayList<ClassInfo>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ArrayList<ClassInfo> getClassInfos() {
        return classInfos;
    }

    private void setClassInfos(ArrayList<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    public ClassScanner() {
    }

    public ClassScanner(String packageName) {
        this.packageName = packageName;
    }
}
