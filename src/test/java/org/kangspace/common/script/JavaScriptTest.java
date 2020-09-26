package org.kangspace.common.script;

import org.kangspace.common.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

/**
 * @author kango2gler@gmail.com
 * @desc javascript 相关测试
 * @date 2017/5/5 14:53
 */
@RunWith(JUnit4.class)
public class JavaScriptTest {

    private static String JAVASCRIPT_ = "javascript";
    /**
     * javascript测试
     * @Author kango2gler@gmail.com
     * @Date 2017/5/5 14:53
     * @return
     */
    @Test
    public void javascriptTest() throws ScriptException, FileNotFoundException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(JAVASCRIPT_);
        Object a = scriptEngine.eval("var a = 1;");
        Object b = scriptEngine.eval("a");

        Reader reader = new InputStreamReader(new FileInputStream(this.getClass().getResource("/").getPath()+"/init.js"));
        Object c = scriptEngine.eval(reader);
        //((RhinoScriptEngine) scriptEngine).invokeFunction("ip","20dot.com")
        //20dot.com/45.78.36.6
        System.out.println(b);
    }

    @Test
    public void classFileTest() throws FileNotFoundException {
        String classFile = "C:\\Users\\kango2gler@gmail.com\\Desktop\\A.class";
        InputStream inputStream = new FileInputStream(classFile);
        if(classFile instanceof Object)
            ;
        if(classFile.getClass().isAssignableFrom(Class.class));
        if(classFile.getClass().isInstance(Class.class));

    }
    @Test
    public void instanceOfTest() throws FileNotFoundException {
        String classFile = "C:\\Users\\kango2gler@gmail.com\\Desktop\\A.class";

        if(classFile instanceof Object)
            System.out.println("classFile instanceof Object");
        //scope: A > B
        if(A.OOMObject.class.isAssignableFrom(A.class))
            System.out.println("A.class.isAssignableFrom(A.OOMObject.class)");
        if(Object.class.isInstance(classFile))
            System.out.println("classFile.getClass().isInstance(");

    }
    volatile int a = 0;
    @Test
    public void volatileTest() throws InterruptedException {
        for (int i =0 ;i< 10; i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    a++;
                }
            });
            t.start();
        }
        while (Thread.activeCount()>1)
            Thread.yield();
        System.out.println(a);

    }
}
