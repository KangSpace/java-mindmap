package org.kangspace.common.designpattern.creational;

import java.util.function.Consumer;

/**
 * 单例模式:
 * (延迟加载模式,立即加载模式)
 * 单例模式的几种方式:
 * 1. 双重检查锁: (延迟加载模式,静态方法)
 * 2. 枚举:(立即加载模式)
 * 3. 静态内部类:(延迟加载模式)
 * 2020/1/17 10:21
 *
 * @author kango2gler@gmail.com
 */
public class SingletonPatternTest {
    /**
     * 双重检查锁单例
     */
    static class DoubleCheckSingleton{
        transient static Integer singleVal;
        transient volatile static DoubleCheckSingleton singleton;
        private DoubleCheckSingleton(){}
        public static DoubleCheckSingleton getInstance(){
            if (singleton == null) {
                synchronized (DoubleCheckSingleton.class) {
                    if (singleton == null) {
                        singleVal = 1;
                        singleton = new DoubleCheckSingleton();
                    }
                }
                return singleton;
            }
            return singleton;
        }
    }

    static void doubleCheckSingletonMain(){
        threadRun((t)->{
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " - " + EnumSingleton.singleton.getInstance());
            }, "doubleCheckSingletonThread").start();
        });
    }

    /**
     * 枚举单例(立即加载模式)
     */
    enum EnumSingleton{
        singleton;
        private SingletonObject singletonObj;
        EnumSingleton(){
            singletonObj = new SingletonObject();
        }
        public SingletonObject getInstance(){
            return this.singletonObj;
        }
    }
    static void enumSingletonMain(){
        threadRun((t)->{
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " - " + EnumSingleton.singleton.getInstance());
            }, "enumSingletonThread").start();
        });


    }

    /**
     * 静态内部类单例(延迟加载模式)
     */
    static class StaticInnerClass{
        private static class InnerClass{
            final static SingletonObject singleton;
            static {
                System.out.println("run");
                singleton = new SingletonObject();
            }
        }
        private StaticInnerClass(){}
        public static SingletonObject getInstance(){
            return InnerClass.singleton;
        }
    }
    static void staticInnerClassSingletonMain(){
        new StaticInnerClass();
        System.out.println("main:");
        threadRun((t)->{
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " - " + StaticInnerClass.getInstance());
            }, "staticInnerClassSingletonThread").start();
        });
    }

    static void main(){
//        enumSingletonMain();
//        doubleCheckSingletonMain();
        staticInnerClassSingletonMain();

    }

    public static void main(String[] args) {
        main();
    }

    static void threadRun(Consumer consumer){
        for(int i=0;i<=3;i++) {
            consumer.accept(i);
        }
    }
    /**
     * 单例实体类
     */
    static class SingletonObject{

    }
}
