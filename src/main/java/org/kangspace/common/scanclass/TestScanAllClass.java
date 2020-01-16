package org.kangspace.common.scanclass;

import org.kangspace.common.annotation.MyClassAnnotation;
import org.kangspace.common.util.ConsoleLogger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 扫描项目中的所有类
 * @date 2017/6/27 17:46
 */
public class TestScanAllClass {
    private ConsoleLogger logger = new ConsoleLogger();

    /**
     * 扫描项目下的所有类
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/27 17:48
     * @return
     */
    public void scanAllClass() throws IOException {
        //当前项目的classLoader,即AppClassLoader,同Thread.currentThread().getContextClassLoader()
        ClassLoader classLoader = this.getClass().getClassLoader();
        logger.info("classLoader:"+classLoader.toString());
        java.util.Enumeration<java.net.URL> urls = classLoader.getResources(".");
        logger.info("Resources : ");
        int i = 0;
        while (urls != null && urls.hasMoreElements()){
            URL url = urls.nextElement();
            //解析jar
            logger.info(url.toString());
            i++;
        }
        logger.info("Resources Total Size : "+i);
    }

    /**
     * 扫描包下的类
     * @param packageName
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/28 11:07
     * @return
     */
    public void scanClass(String packageName) throws IOException {
        long startTime = System.currentTimeMillis();
        ClassScanner cs = new ClassScanner(packageName);
        ArrayList<ClassInfo> classInfos = cs.scanClasses();
        for (ClassInfo classInfo : classInfos) {
            logger.info(classInfo.getJarName()+" "+classInfo.getName());
        }
        logger.info("classInfos size:"+classInfos.size()+" , 耗时: "+(System.currentTimeMillis()-startTime) +" ms");

        logger.info("存在Annotation的类:");
        List<ClassInfo> classAnns = cs.findClassByAnnotation(MyClassAnnotation.class);
        for (ClassInfo classInfo : classAnns) {
            logger.info(classInfo.getJarName()+" "+classInfo.getName());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String packageName = "com._20dot";//
        TestScanAllClass test = new TestScanAllClass();
        test.scanClass(packageName);

    }
}
