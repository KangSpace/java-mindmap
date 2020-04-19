package org.kangspace.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kango2gler@gmail.com
 * @desc
 * @date 2017/5/5 18:01
 */
public final class A {
    private volatile transient String a;
    public native void nativeMethod();

    public void  a() {
        java.lang.Compiler.disable();
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            OOMObject obj = new OOMObject();
            list.add(obj);
            byte[] b  = obj.b;
            System.out.println(list);
        }

    }

    /**
     * Test Obj
     * @Author kango2gler@gmail.com
     * @Date 2017/5/6 11:17
     * @return
     */
    public static class OOMObject{
        public byte[] b ;

        public OOMObject() {
            b = new byte[1024*1024*1024*1024];
            int i=-1;
            while (i<b.length){
                i++;
                b[i]=99;
            }
        }
    }
}
