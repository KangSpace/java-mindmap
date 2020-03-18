package org.kangspace.common.designpattern.behavioral;

/**
 * 备忘录模式
 * 概述: 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 * 这样以后可将该对象恢复到原先保存的状态。
 * 适用性:
 *      1. 必须保存一个对象在某个时刻的(部分)状态，这样以后需要时它才能恢复到先前的状态
 *      2. 如果一个用接口来让其他对象得到这些状态，将会暴露对象的实现细节并破坏对象的封装性。
 * 参与者:
 *      1. Memento 备忘录:存储原发器对象的内部状态
 *      2. Originator 原发器: 创建一个备忘录，用以记录当前时刻它的内部状态。
 *          使用备忘录回复内部状态
 *      3. Caretaker 管理者: 负责保存好备忘录
 *          不能对备忘录的内容进行操作或检查
 *
 * @author kangxuefeng@etiantian.com
 */
public class MementoTest {
    /**
     * 备忘录
     * 存储原发器内部状态
     */
    static class Memento{
        private String state;
        public Memento(String state){
            this.state = state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    /**
     * 原发器
     * 创建备忘录，用以记录当前内部状态
     * 使用备忘录回复内部状态
     */
    static class Originator{
        private String state;

        public Originator(String state) {
            this.state = state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        /**
         * 创建备忘录
         * @return
         */
        public Memento createMemento(){
            return new Memento(getState());
        }

        public void resolveMemento(Memento memento){
            setState(memento.getState());
        }
    }

    /**
     *  管理者:保存备忘录
     *  不能对备忘录内容进行操作
     */
    static class Caretaker{
        private Memento memento;

        public Caretaker(Memento memento) {
            this.memento = memento;
        }

        public Memento getMemento() {
            return memento;
        }

        public void setMemento(Memento memento) {
            this.memento = memento;
        }
    }

    static void main(){
        Originator org = new Originator("开始ing");
        Caretaker caretaker = new Caretaker(org.createMemento());
        System.out.println("==== 当前状态:");
        System.out.println(org.getState());
        org.setState("暂停");
        System.out.println("==== 切换状态:");
        System.out.println(org.getState());
        System.out.println("==== 恢复状态:");
        org.resolveMemento(caretaker.getMemento());
        System.out.println(org.getState());
    }
    public static void main(String[] args) {
        main();
    }
}
