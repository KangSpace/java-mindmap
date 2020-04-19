package org.kangspace.common.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 概述: 定义对象的一种一对多的依赖关系，当一个对象的状态方式改变时，所有依赖它的对象都得到通知被自动更新
 * 适用性:
 *      1. 当一个抽象模型有2个方面，其中一个方面依赖另一个方面。
 *          将二者封装在独立的对象中以使他们可以各自独立的改变和复用
 *      2. 当一个对象的改变需要同时改变其他对象时，而不知道具体有多少个对象有待改变
 *      3. 当一个对象必须通知其他对象时，而不能假定其他对象是谁
 * 参与者:
 *      1. Subject: 目标对象
 *              目标知道它的观察者。可以有任意多个观察者观察同一个目标
 *              提供注册和删除观察者的接口。
 *      2. Observer: 观察者
 *              为那些在目标发生改变时需要获得通知的对象定义一个更新接口
 *      3. ConcreteSubject: 具体目标对象
 *              将有关状态存入ConcreteObserver对象的引用，存储有关状态。
 *              当它的状态发生改变时，向它的各个观察者发出通知
 *      4. ConcreteObserver: 具体观察者
 *              维护一个指向ConcreteSubject的引用。存储有关状态，这些状态应与目标状态保持一致。
 *              实现Observer的更新接口以使自身的状态与目标状态一致
 *
 *
 *
 * @author kango2gler@gmail.com
 */
public class ObserverPatternTest {
    /**
     * 目标对象
     */
    interface Subject{
        void submit(String content);

        /**
         * 注册观察者
         */
        void registerListener(Observer observer);

        /**
         * 删除观察者
         */
        void removeListener(Observer observer);

        String getContent();
    }

    /**
     * 具体目标:订单
     */
    static class OrderSubject implements Subject {
        List<Observer> observers = new ArrayList<>();
        private String content;
        @Override
        public void submit(String content) {
            this.content = content;
            System.out.println("订单提交:"+content);
            //通知观察者
            observers.forEach(t->{
                t.notice(this);
            });
        }

        @Override
        public void registerListener(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeListener(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * 观察者
     */
    interface Observer{
        void notice(Subject subject);
    }

    /**
     * 具体观察者: 微信通知
     */
    static class WXMessageSenderObserver implements Observer {

        @Override
        public void notice(Subject subject) {
            System.out.println("微信通知成功: " + subject.getContent());
        }
    }
    /**
     * 具体观察者: 短信通知
     */
    static class SMSMessageSenderObserver implements Observer {

        @Override
        public void notice(Subject subject) {
            System.out.println("短信通知成功: " + subject.getContent());
        }
    }


    static void main(){
        OrderSubject order = new OrderSubject();
        order.registerListener(new WXMessageSenderObserver());
        order.registerListener(new SMSMessageSenderObserver());
        order.submit("购买图书下单");
    }
    public static void main(String[] args) {
        main();
    }
}
