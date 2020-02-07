package org.kangspace.common.designpattern.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 * 概述: 为其他对象提供一种代理以控制对该对象的访问
 * 使用性:
 *       1.远程代理（RemoteProxy）为一个对象在不同的地址空间提供局部代表。
 *     2.虚代理（VirtualProxy）根据需要创建开销很大的对象。
 *     3.保护代理（ProtectionProxy）控制对原始对象的访问。
 *     4.智能指引（SmartReference）取代了简单的指针，它在访问对象时执行一些附加操作。
 *
 * @author kangxuefeng@etiantian.com
 */
public class ProxyPatternTest {
    /**
     * 抽象类
     */
    interface NormalObject{
        void action(String desc);
    }

    /**
     * 被代理对象
     */
    static class NormalObjectImpl implements NormalObject {
        @Override
        public void action(String desc) {
            System.out.println("普通对象实现类:" +desc);
        }
    }

    /**
     * 1. 代理类,静态代理
     */
    static class ProxyNormalObject implements NormalObject {
        private NormalObject object;
        public ProxyNormalObject(NormalObject object){
            this.object = object;
        }

        @Override
        public void action(String desc) {
            System.out.println("代理前执行:"+desc);
            object.action(desc);
            System.out.println("代理后执行:"+desc);
        }
    }

    /**
     * 2. 动态代理,即JDK代理，利用jdk提供的代理方法
     */
    static NormalObject dynamicProxyFn(NormalObject object){
        return (NormalObject) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理前执行");
                //执行目标对象方法
                Object returnValue = method.invoke(object, args);
                System.out.println("动态代理后执行");
                return returnValue;
            }
        });
    }

    /**
     * 3. CGLIB 代理
     */

    static void main(){
        NormalObject object = new NormalObjectImpl();
        System.out.println("###静态代理(普通代理):");
        ProxyNormalObject proxy = new ProxyNormalObject(object);
        proxy.action("action");

        System.out.println("###动态代理(JDK代理):");
        NormalObject proxy2 = dynamicProxyFn(object);
        proxy2.action("动态代理action");


    }
    public static void main(String[] args) {
        main();
    }
}
