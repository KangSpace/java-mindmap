package org.kangspace.common.designpattern.structural;

import java.util.Arrays;

/**
 *
 * 适配器模式
 * 将一个类的接口转换成客户需要的另一个接口
 * 适用性:
 * 1. 已存在的类不符合需求
 * 2. 创建一个与其他类不相关或不兼容的类
 * 3. （仅适用于对象Adapter）你想使用一些已经存在的子类，但是不可能对每一个都进行
 *       子类化以匹配它们的接口。对象适配器可以适配它的父类接口。
 * 适配器模式的三种情况:
 * 1. 类适配器
 *    通过继承类，实现对某个类方法的调用
 * 2. 对象适配器
 *    通过传入类实例对象，实现对类方法的调用
 * 3. 接口适配器
 *    对于一个存在n个方法的接口，只会用到某几个方法，通过构造抽象类实现(实现方法体为空)该接口的所有方法，
 *    然后继承该抽象类实现所需的方法。
 *
 * @author kangxuefeng@etiantian.com
 */
public class AdapterPatternTest {
    /**
     * 1. 类适配器模式
     * 原理: 通过继承实现适配器功能
     * 通过适配器在B类方法中调用A类的方法
     */
    interface Ps2{
        void isPs2();
    }
    interface Usb{
        void isUsb();
    }

    /**
     * USB的具体实现
     */
    static class Usber implements Usb {
        @Override
        public void isUsb() {
            System.out.println("USB接口");
        }
    }

    /**
     * 适配器:用于将Ps2转换为USB
     * 在Ps2 中想要适配Usb的isUse方法
     */
    static class UsbClassAdapter extends Usber implements Ps2 {
        @Override
        public void isPs2() {
            isUsb();
        }
    }

    /**
     * 1. 类适配器测试
     */
    static void classAdapterMain(){
        Usber usbAdapter = new UsbClassAdapter();
        usbAdapter.isUsb();
        Ps2 ps2UsbAdapter = new UsbClassAdapter();
        ps2UsbAdapter.isPs2();
    }


    /**
     * 2. 对象适配器模式
     * 原理：通过组合来实现适配器功能。
     * (通过传入别的对象来实现对其他对象方法的调用)
     */
    static class UsbObjectAdapter implements Ps2 {
        private Usber usber;

        public UsbObjectAdapter(Usber usber) {
            this.usber = usber;
        }

        @Override
        public void isPs2() {
            usber.isUsb();
        }
    }
    /**
     * 2.对象适配器测试
     */
    static void objectAdapterMain(){
        Usber usber = new Usber();
        Ps2 ps2UsbAdapter = new UsbObjectAdapter(usber);
        ps2UsbAdapter.isPs2();
    }

    /**
     * 3. 接口适配器模式
     * 原理: 通过抽象类来实现适配
     * (用于某个接口中定义了N个方法,但我们只会使用其中的某几个接口)
     */
    /**
     * 被适配接口
     */
    interface Ps2More{
        void isPs2();
        void ps2fn1();
        void ps2fn2();
        void ps2fn3();
        void ps2fn4();
        void ps2fn5();
    }
    static abstract class AbstractPs2Adapter{
        void isPs2(){};
        void ps2fn1(){};
        void ps2fn2(){};
        void ps2fn3(){};
        void ps2fn4(){};
        void ps2fn5(){};
    }

    static class Ps2Adapter extends AbstractPs2Adapter {
        @Override
        void isPs2(){
            System.out.println("isPs2 revoke");
        };
    }

    /**
     * 3. 接口适配器测试
     */
    static void interfaceAdapterMain(){
        Ps2Adapter ps2 = new Ps2Adapter();
        ps2.isPs2();
    }

    static void main(){
        //1. 类适配器
//        classAdapterMain();
        //2. 对象适配器
//        objectAdapterMain();
        //3. 接口适配器
        interfaceAdapterMain();
        Arrays.asList(1, 2, 3);
    }
    public static void main(String[] args) {
        main();
    }
}
