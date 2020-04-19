package org.kangspace.common.designpattern.behavioral;

/**
 * 中介者模式
 * 概述: 用一个中介对象来封装一系列对象交互。中介者使各个对象不需要显示的相互引用，从而使其耦合松散，而且可以独立的改变他们的交互
 * 适用性:
 *      1. 一组对象以定义良好但是复杂的方式通讯。产生的相互依赖关系结构混乱且难以理解
 *      2. 一个对象引用其他很多对象，并且直接与这些对象通讯，导致难以复用该对象
 *      3. 想定制一个分布在多给类中的行为，又不想生成太多子类。
 * 参与者:
 *      1. Mediator 中介者:定义一个接口与各同事(Colleague)对象通信
 *      2. ConcreteMediator 具体中介者实现: 通过协调各同事对象实现协作行为
 *              了解并维护它的各个同事
 *      3. Colleagueclass 同事类: 每个同事类都知道它的中介对象。
 *              每一个同事对象在需要与其他对象通信时，与它的中介者通信
 *
 * @author kango2gler@gmail.com
 */
public class MediatorTest {
    /**
     * 中介者，定义与各同事通信的接口
     */
    interface Mediator{
        void notice(String c);
        void aCallB();
        void bCallA();
    }

    /**
     * 具体中介者实现，了解维护各同事对象，并协调各对象的通信
     */
    static class ConcreteMediator implements Mediator{
        Colleague colleagueA;
        Colleague colleagueB;

        public ConcreteMediator(){
            this.colleagueA = new ColleagueA(this);
            this.colleagueB = new ColleagueB(this);
        }

        @Override
        public void notice(String c) {
            if ("a".equals(c)) {
                colleagueA.action();
            }
            if ("b".equals(c)) {
                colleagueB.action();
            }
        }

        @Override
        public void aCallB() {
            ((ColleagueA)colleagueA).called(colleagueB);
        }

        @Override
        public void bCallA() {
            ((ColleagueB)colleagueB).called(colleagueA);
        }
    }

    /**
     * 同事对象接口
     */
    interface Colleague{
        void action();

        void called(Colleague colleague);
    }

    /**
     * 同事对象具体实现
     */
    static class ColleagueA implements Colleague {
        ConcreteMediator concreteMediator;
        public ColleagueA(ConcreteMediator concreteMediator) {
            this.concreteMediator = concreteMediator;
        }

        @Override
        public void action() {
            System.out.println("ColleagueA action!!!");
        }

        @Override
        public void called(Colleague colleague) {
            System.out.println("被"+colleague+"调用");
        }
        public void callB(){
            concreteMediator.aCallB();
        }
    }
    /**
     * 同事对象具体实现
     */
    static class ColleagueB implements Colleague {
        ConcreteMediator concreteMediator;
        public ColleagueB(ConcreteMediator concreteMediator) {
            this.concreteMediator = concreteMediator;
        }
        @Override
        public void action() {
            System.out.println("ColleagueB action!!!");
        }

        @Override
        public void called(Colleague colleague) {
            System.out.println("被"+colleague+"调用");
        }
        public void callA(){
            concreteMediator.bCallA();
        }
    }


    static void main(){
        Mediator mediator = new ConcreteMediator();
        System.out.println("------------ 中介者处理调用各同事");
        mediator.notice("a");
        mediator.notice("b");
        System.out.println("------------ 中介者处理同事间通讯 a调用b方法");
        mediator.aCallB();
        System.out.println("------------ 中介者处理同事间通讯 b调用a方法");
        mediator.bCallA();
    }
    public static void main(String[] args) {
        main();
    }
}
