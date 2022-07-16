package org.kangspace.common.algorithms;

/**
 * 交换两个整型变量，不使用第三个变量，实现两个变量的交换
 *
 * @author kango2gler@gmail.com
 * @since 2022/7/7
 */
public class TwoIntegerSwap {

    /**
     * 方式1: 将a存放在a的高32位,b存在到a的低32位
     * 前提: a,b 需要使用Long类型
     * @param a a
     * @param b b
     */
    public static void impl1(Long a, Long b) {
        System.out.println("方式1:(将a存放在a的高32位,b存在到a的低32位)");
        System.out.println("src: a:" + a + ",b:" + b);
        // 1. 将a放入a的高32位,将b放入a的低32位
        a = a << 32 | b;
        // 取1中a的高32位为b
        b = a >> 32;
        // 取1中a的低32位
//        a = a << 32 >> 32;
        a = a & 0xFFFF;
        System.out.println("dest: a:" + a + ",b:" + b);
    }

    /**
     * 方式2: a 中存放a+b的和,再用a-b分别求出b和a的值
     * @param a a
     * @param b b
     */
    public static void impl2(Integer a, Integer b) {
        System.out.println("方式2:(a 中存放a+b的和,再用a-b分别求出b和a的值)");
        System.out.println("src: a:" + a + ",b:" + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("dest: a:" + a + ",b:" + b);
    }

    /**
     * 方式3: 使用异或的方式算出a,b互换值
     * @param a
     * @param b
     */
    public static void impl3(Integer a, Integer b) {
        System.out.println("方式3:(使用异或的方式算出a,b互换值)");
        System.out.println("src: a:" + a + ",b:" + b);
        // 1. 按位异或,相同为0,不同为1
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("dest: a:" + a + ",b:" + b);

    }

    public static void main(String[] args) {
        Integer a = 3, b = 5;
        Long aLong = a.longValue(), bLong = b.longValue();
        impl1(aLong, bLong);
        impl2(a, b);
        impl3(a, b);
    }
}
