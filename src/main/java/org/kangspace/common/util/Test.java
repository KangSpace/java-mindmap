package org.kangspace.common.util;

/**
 * @author kangxuefeng@etiantian.com
 * @desc
 * @date 2017/6/30 16:30
 */
public class Test {
    public static void a(){
        System.out.println("a");
    }
    public void b(){
        System.out.println("b");
    }

    public static void main(String[] args) {
        Test t = new Test();
        t = null;
//        t.a();
//        ((Test)null).a();
        Test a = ((Test)null); //== Test a = null;
        a.b();
    }
}
