package org.kangspace.common.classloader;

import org.kangspace.common.A;
import org.kangspace.common.util.ConsoleLogger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * @author kangxuefeng@etiantian.com
 * @desc classloader测试
 * @date 2017/6/21 18:17
 */
public class TestClassloader {
    //Logger logger = Logger.getLogger(this.getClass().getName());
    static ConsoleLogger logger = new ConsoleLogger();
    /**
     * @param
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/22 11:48
     * @return
     */
    public Class classLoaderLoadedOrderTest(String path ,String name) throws ClassNotFoundException, NoSuchMethodException, MalformedURLException, IllegalAccessException, InvocationTargetException {
        NetworkClassLoader ncl = new NetworkClassLoader(path);
        if(name != null) {
            Class clazz = ncl.loadClass(name);
            logger.info("==START==");
            if (clazz != null) {
                logger.info("==CLASS==:" + clazz.getName());
                ClassLoader parent = clazz.getClassLoader();
                logger.info("==CURRENT ClassLoader==:" + parent);
                while (parent != null) {
                    parent = parent.getParent();
                    logger.info("==PARENT  ClassLoader==:" + parent);
                }
            }
            logger.info("==END==");
            return clazz;
        }else{
            logger.info("==load jars==");
            ncl.loadJars(false);
            logger.info("==load jars END==");
        }
        return null;
    }
    /**
     * 加载器测试
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 10:40
     * @return
     */
    public void test1(TestClassloader tcl) throws ClassNotFoundException, NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String path = "D:\\class_test_path\\";
        String className = "com._20dot.common.A";
        Class aClass = tcl.classLoaderLoadedOrderTest(path,className);
        String className2 = "com.etiantian.dayuwen.util.Constants";
        Class class2 = tcl.classLoaderLoadedOrderTest(path,className2);
        String path2 = "D:\\class_test_path2\\";
        String className3 = "com.etiantian.dayuwen.util.Constants";
        Class class3 = tcl.classLoaderLoadedOrderTest(path,className3);

        if (aClass != null) {
            A a = (A) aClass.newInstance();
            logger.info("class== "+a.getClass().toString());
        }

        if (class2!=null && class3 != null) {
            logger.info("==============================================");
            logger.info("class2 =="+class2.getName());
            logger.info("class3 =="+class3.getName());
            logger.info("class2 isAssignableFrom class3:"+(class2.isAssignableFrom(class3)));
            logger.info("class2 isInstance class3:"+(class2.isInstance(class3)));
        }
    }
    /**
     * jar包加载类测试
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 10:39
     */
    public void test2() throws ClassNotFoundException, NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException {
        String protocol = "file:///";
        //String path = "D:\\class_test_path\\";//该路径下有test.jar,使用加载jar包方式加载,加载器: sun.misc.Launcher$AppClassLoader@42e816
        String path = "D:\\class_test_path2\\";//该路径下为class文件,使用自定义加载器加载,加载器: com._20dot.common.classloader.NetworkClassLoader
        String className2 = "com.etiantian.dayuwen.util.Constants";
        String jarFileName = className2;
        //load jar
        String jarName = "test.jar";
        //加载jar
        this.classLoaderLoadedOrderTest(path,null);
        //加载类
        this.classLoaderLoadedOrderTest(path,className2);
    }

    /**
     * 热更新测试
     * 将文件夹下的class文件替换可重新获取到新的内容
     * 需要为解压的class文件,不能为jar,jar包在加载后无法替换
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 10:39
     * @return
     */
    public void test3() throws InterruptedException, ClassNotFoundException, NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        String path = "D:\\class_test_path2\\";//该路径下为class文件,使用自定义加载器加载,加载器: com._20dot.common.classloader.NetworkClassLoader
        String className2 = "com.etiantian.dayuwen.util.Constants";
        //1分钟加载1次jar包
        for (int i = 10;i>0;i--) {
            //加载jar
            this.classLoaderLoadedOrderTest(path,null);
            //加载类
            Class clazz = this.classLoaderLoadedOrderTest(path,className2);
            Object co = clazz.newInstance();
            //static
            Field field = clazz.getField("PPT_SUFFIX_TYPE_REGULAR");
            //final static
            Field field2 = clazz.getField("SPECIAL_CHAR_REGULAR");
            logger.info(field.getDeclaringClass().toString());
            logger.info(field.getModifiers()+" "+ field.getType() +" "+ field.toGenericString() +" "+ field.getName() + " = " + field.get(co));
            logger.info(field2.getDeclaringClass().toString());
            logger.info(field2.getModifiers()+" "+ field2.getType() +" "+ field2.toGenericString() +" "+ field2.getName() + " = " + field2.get(co));

            Thread.sleep(1000*60);
            /*
            替换前:
            class com.etiantian.dayuwen.util.Constants
            9 class java.lang.String public static java.lang.String com.etiantian.dayuwen.util.Constants.PPT_SUFFIX_TYPE_REGULAR PPT_SUFFIX_TYPE_REGULAR = (.ppt|.pptx)$
            class com.etiantian.dayuwen.util.Constants
            25 class java.lang.String public static final java.lang.String com.etiantian.dayuwen.util.Constants.SPECIAL_CHAR_REGULAR SPECIAL_CHAR_REGULAR = .*('|"|\=|\+|-|\*|\/|\|\!|\%|\^|\&|\$|\(|\)|\?|\,|\.|\:|;|\{|\}|\|).*
            替换后:
            class com.etiantian.dayuwen.util.Constants
            9 class java.lang.String public static java.lang.String com.etiantian.dayuwen.util.Constants.PPT_SUFFIX_TYPE_REGULAR PPT_SUFFIX_TYPE_REGULAR = 1
            class com.etiantian.dayuwen.util.Constants
            25 class java.lang.String public static final java.lang.String com.etiantian.dayuwen.util.Constants.SPECIAL_CHAR_REGULAR SPECIAL_CHAR_REGULAR = 2
            */
        }
    }
    /**
     * jar包解压测试
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 14:37
     * @return
     */
    public void test4() throws IOException {
        logger.info("===START===");
        String jarPath = "D:\\class_test_path\\";
        String fileName = "test.jar";
        NetworkClassLoader.unJar(new File(jarPath+fileName),jarPath);
        logger.info("===END===");
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException, NoSuchFieldException, InterruptedException {
        TestClassloader tcl = new TestClassloader();
//        tcl.test3();
        tcl.test4();



    }

}
