package org.kangspace.common;

/**
 * @author kango2gler@gmail.com
 * @desc Systems测试
 * @date 2017/7/3 9:38
 */
public class TesSystems {
    public static void main(String[] args) {
//        System.out.println(Systems.getContext());
        String a = "a";
        String b = "a";
        String c = new String("a");
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(c==a);
        System.out.println(c.intern().hashCode());
    }
}
