package org.kangspace.common.designpattern.behavioral;

/**
 * 状态模式
 * 概述: 当一个对象的内在状态改变时允许改变其行为，这个对象看起来是改变了其类
 * 适用性;
 *      1. 一个对象的行为取决于它的状态，并且它必须在运行时刻根据状态改变它的行为
 *      2. 一个操作中包含庞大的多分支的条件语句，并且这些分支依赖于该对象的状态。
 *         这个状态通常用一个或多个枚举常量表示。
 *         通常，有多个操作包含这一相同的条件结构体。
 *         State模式将每个条件分支放入一个独立的类中，这使得可以根据对象自身的情况将对象的状态
 *         作为一个对象，这一对象可以不依赖其他对象而独立变化。
 * 参与者:
 *      1. Context 上下文: 定义客户感兴趣的接口
 *                 维护一个ConcreteState子类的实例，这个实例定义当前状态
 *      2. State 状态接口, 定义一个接口以封装与Context的一个特定状态相关的行为
 *      3. ConcreteStatesubclasses 具体状态子类:
 *              每一个子类实现一个与Context的一个状态相关的行为
 * 实例:
 *      订单状态变化使用状态模式
 * @author kango2gler@gmail.com
 */
public class StatePatternTest {
    /**
     * Context: 上下文,定义客户感兴趣的接口，维护ConcreteState对象实例，定义当前状态
     */
    static class StateContext{
        State state;

        public StateContext(State state) {
            this.state = state;
        }
        void handle(){
            this.state.stateHandle();
        }

        public void setState(State state) {
            this.state = state;
        }

        public State getState() {
            return state;
        }
    }

    /**
     * State: 状态接口
     * 定义一个接口封装与Context的一个特定状态相关的行为
     */
    interface State{
        void stateHandle();
    }

    /**
     * ConcreteState :具体状态类，实现与Context一个特定状态的相关行为
     */
    static class OrderedState implements State{

        @Override
        public void stateHandle() {
            System.out.println("OrderedState: 已下单");
        }
    }
    /**
     * ConcreteState :具体状态类，实现与Context一个特定状态的相关行为
     */
    static class InTransferState implements State{

        @Override
        public void stateHandle() {
            System.out.println("InTransferState: 配送中");
        }
    }

    /**
     * ConcreteState :具体状态类，实现与Context一个特定状态的相关行为
     */
    static class OrderFinishState implements State{
        @Override
        public void stateHandle() {
            System.out.println("OrderFinishState: 订单结束");
        }
    }

    static void main(){
        OrderedState orderedState = new OrderedState();
        StateContext context = new StateContext(orderedState);
        context.handle();
        context.setState(new InTransferState());
        context.handle();
        context.setState(new OrderFinishState());
        context.handle();
    }
    public static void main(String[] args) {
        main();
    }
}
