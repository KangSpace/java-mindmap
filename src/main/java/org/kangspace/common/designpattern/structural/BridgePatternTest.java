package org.kangspace.common.designpattern.structural;

/**
 * 桥接模式
 * 概述: 将抽象部分与实现部分分离，使二者可以独立变化。
 * 这种模式涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
 * 注意点：
 * 　　　　1、定义一个桥接口，使其与一方绑定，这一方的扩展全部使用实现桥接口的方式。
 * 　　　　2、定义一个抽象类，来表示另一方，在这个抽象类内部要引入桥接口，而这一方的扩展全部使用继承该抽象类
 *
 * @author kango2gler@gmail.com
 */
public class BridgePatternTest {
    interface Qiao{
        //目的地B
        void targetAreaB();
    }

    /**
     *  目的地B1,B2
     */
    static class AreaB1 implements Qiao {

        @Override
        public void targetAreaB() {
            System.out.println("我要去B1");
        }
    }
    static class AreaB2 implements Qiao {

        @Override
        public void targetAreaB() {
            System.out.println("我要去B2");
        }
    }

    /**
     *  抽象来源地A：AreaA
     */
    static abstract class AreaA{
        Qiao qiao;
        abstract void fromAreaA();
    }
    /**
     * 来源地A1
     */
    static class AreaA1 extends AreaA {
        @Override
        void fromAreaA() {
            System.out.println("我来自A1");
        }
    }
    /**
     * 来源地A2
     */
    static class AreaA2 extends AreaA {
        @Override
        void fromAreaA() {
            System.out.println("我来自A2");
        }
    }
    static void bridgeMan(){
        AreaA a = new AreaA2();
        a.qiao = new AreaB1();
        a.fromAreaA();
        a.qiao.targetAreaB();
    }

    static void main(){
        bridgeMan();
    }
    public static void main(String[] args) {
        main();
    }
}
