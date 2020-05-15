package org.kangspace.common;

import java.util.HashMap;

/**
 *
 */
public class JavaBasicTenQuesTest {
    static void q1() {
        System.out.print("q1: ");
        System.out.println("float a = 0.125f; double b = 0.125d; System.out.println((a - b) == 0.0);(true,float 与double 计算时,先将float转换为double再计算,0.125F精度与0.125D相同)");
        float a = 0.125f; double b = 0.125d; System.out.println((a - b) == 0.0);
        System.out.println();
    }
    static void q2() {
        System.out.print("q2: ");
        System.out.println("double c = 0.8; double d = 0.7; double e = 0.6; 那么 c-d 与 d-e 是否相等？(false,(c-d):0.10000000000000009\n(d-e):0.09999999999999998)");

        double c = 0.8; double d = 0.7; double e = 0.6;
        System.out.println("(c-d):" + (c-d));
        System.out.println("(d-e):" + (d-e));
        System.out.println((c-d) == (d-e));
        System.out.println();
    }
    static void q3() {
        System.out.print("q3: ");
        System.out.println("System.out.println(1.0 / 0); 的结果是什么？(Infinity: 0/0:java.lang.ArithmeticException: / by zero ,0.0f/0.0f (or 0.0d/0.0d or 0.0/0.0): NaN , 1.0f/0.0f(or 1.0d/0.0d or 1.0/0.0): Infinity 无穷大(正/负))");
        System.out.println(1.0 / 0);
        System.out.println();
    }
    static void q4() {
        System.out.print("q4: ");
        System.out.println("System.out.println(0.0 / 0.0); 的结果是什么？(NaN: 0/0:java.lang.ArithmeticException: / by zero ,0.0f/0.0f (or 0.0d/0.0d or 0.0/0.0): NaN , 1.0f/0.0f(or 1.0d/0.0d or 1.0/0.0): Infinity 无穷大(正/负)))");
        System.out.println(0.0 / 0.0);
        System.out.println(Double.isNaN(0.0 / 0.0));
        Float.isNaN(0.0F / 0.0F);
        System.out.println(1.0/0);
        System.out.println(0/0);
        System.out.println();
    }
    static void q5() {
        System.out.print("q5: ");
        System.out.println(">> 和 >>> 的区别是？(D,浮点数不能>>和>>>)");
        System.out.println("A. 任何整数没有区别");
        System.out.println("B. 负整数一定没有区别");
        System.out.println("C. 浮点数可以 >> 运算，但是不可以 >>> 运算");
        System.out.println("D. 正整数一定没有区别");

        System.out.println("正整数 1>>: "+(1>>1)+", >>>"+(1>>>1));
        System.out.println("负整数 -1>>: "+(-1>>1)+", >>>"+(-1>>>1));
        System.out.println(1L>>1);
//        System.out.println("浮点数>>: "+(1.0D>>1)+", >>>"+(1.0D>>>1));
        System.out.println();
    }
    static void q6() {
        System.out.print("q6: ");
        System.out.println("某个类有两个重载方法：void f(String s) 和 void f(Integer i)，那么 f(null) 的会调用哪个方法？(编译出错,Ambiguous method call.)");
//        f(null);
        System.out.println();
    }

    void f(String s) {

    }
    void f(Integer s) {

    }

    static void q7() {
        System.out.print("q7: ");
        System.out.println("某个类有两个重载方法：void g(double d) 和 void g(Integer i)，那么 g(1) 的会调用哪个方法？(void g(double i)):" +
                "基本数据类型转换优先级: byte,char,short,int->int "+
                "int 之后大小: long,float,double");
        g('1');
        g((byte)(1));
        g(1);
        g(1F);
        g(1D);
        g(Integer.valueOf(1));
        System.out.println();

    }

    static void g(double d) {
        System.out.println("double:"+d);
    }
    static void g1(int i) {
        System.out.println("int:"+i);
    }
    static void g2(float f) {
        System.out.println("float:"+f);
    }
    static void g(Integer s) {
        System.out.println("Integer:"+s);
    }
    static void g(Byte b) {
        System.out.println("Byte:"+b);
    }
    static void g(Double s) {
        System.out.println("Integer:"+s);
    }
    static void g(Character c) {
        System.out.println("Integer:"+c);
    }

    static void q8() {
        System.out.print("q8: ");
        System.out.println("String a = null; switch(a) 匹配 case 中的哪一项？(NPE)");
        String a = null;
        try {
            switch(a){
                case "":
                    System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    static void q9() {
        System.out.print("q9: ");
        System.out.println("<String, T, Alibaba> String get(String string, T t) { return string; } 此方法：(编译正确)");
        System.out.println();
    }
    static <String, T, Alibaba> String get(String string, T t) { return string; }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE: n + 1;
    }
    static void q10() {
        System.out.print("q10: ");
        System.out.println("HashMap 初始容量 10000 即 new HashMap(10000)，当往里 put 10000 个元素时，需要 resize 几次（初始化的那次不算）？(1次," +
                "当++size>threshold(创建Map指定容量时,该值=容量,不指定时,默认为0.75*16,扩容后容量>16时值为新容量值,<16时,值为旧容量*0.75) 时" +
                ",触发一次resize; 若创建HashMap时指定容量A,则resize时新容量为2倍旧容量," +
                "若创建时未指定容量时,则默认为16大小)");
        System.out.println("tableSizeFor(initialCapacity):"+tableSizeFor(1));
        HashMap<String,String> map = new HashMap(6);
        map.put("1","1");
        map.put("2","2");
        map.put("3","2");
        map.put("4","2");
        map.put("5","2");
        map.put("6","2");

    }
    static void q11() {
        System.out.println(Float.POSITIVE_INFINITY == Float.POSITIVE_INFINITY);
        System.out.println(Float.NEGATIVE_INFINITY == Float.NEGATIVE_INFINITY);
    }
    static void main(){
        q1();
        q2();
        q3();
        q4();
        q5();
        q6();
        q7();
        q8();
        q9();
        q10();
        q11();

    }
    public static void main(String[] args) {
        main();
    }
}
