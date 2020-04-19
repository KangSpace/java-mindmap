package org.kangspace.common.jdk18;

/**
 * 2020/1/7 18:03
 *
 * @author kango2gler@gmail.com
 */
public class RuntimeConstantPoolOOm {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);
        String java = new String("java");
        System.out.println(java.intern() == java);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }
}
