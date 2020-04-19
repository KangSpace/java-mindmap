package org.kangspace.common.designpattern.structural;

/**
 *  门面模式:
 *  概述: 为一系列子系统，或子系统中的一系列接口提供一个统一的访问界面。
 *  适用性:
 *      1. 为一个复杂的子系统提供一个简单接口时
 *      2. 客户端与抽象类的实现部分存在着很大的耦合,使用门面将子系统与客户端和其他子系统分离
 *      3. 构建一个有层次的子系统时，引入门面可以为各子系统提供一个统一的入口，使得各系统提供仅通过门面来通信。
 *  样例:
 *      Controller 层访问Service层方法，可将各个Service注册到一个门面上，在Controller可以直接通过门面入口访问各个Service
 * @author kango2gler@gmail.com
 */
public class FacadePatternTest {

    interface IFacade{
        Service1 getService1();
        Service2 getService2();
    }

    static class Facade implements IFacade {

        @Override
        public Service1 getService1() {
            return new Service1();
        }

        @Override
        public Service2 getService2() {
            return new Service2();
        }
    }

    static class Service1 {
        public void do1(){
            System.out.println("Service1.do1()");
        }
    }
    static class Service2 {
        public void do2(){
            System.out.println("Service1.do2()");
        }
    }

    static void main(){
        IFacade facade = new Facade();
        facade.getService1().do1();
        facade.getService2().do2();

    }
    public static void main(String[] args) {
        main();
    }
}
