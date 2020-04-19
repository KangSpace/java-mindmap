package org.kangspace.common.designpattern.behavioral;

/**
 * 策略模式
 * 概述: 定义一系列算法，把它们一个一个封装起来，并且可以使它们相互替换。
 *       本模式可以使算法独立于使用它的客户而变化
 * 适用性:
 *      1. 许多相关的类仅仅是行为有异。“策略”提供了一种用多个行为中的一个行为来配置一个类的方法
 *      2. 需要使用一个算法的不同变体
 *      3. 算法使用客户不应该知道的数据。可使用策略模式以避免暴露复杂的、与算法相关的数据结构。
 *      4. 一个类定义了多种行为，并且这些行为在这个类的操作中以多个条件语句的形式出现。
 *         将相关的条件分支移入它们各自的Strategy类中以代替这些条件语句。
 *
 *
 * 参与者:
 *      1. Strategy 抽象策略类，定义所有支持的算法的公共接口。
 *                  Context  使用这个接口来调用ConcreteStrategy定义的算法
 *      2. ConcreteStrategy 具体策略类，以Strategy实现某具体算法
 *      3. Context 上下文对象。用一个ConcreteStrategy对象来配置
 *      维护一个对Strategy对象的引用
 *      可定义一个接口让Strategy访问它的数据
 *
 * 样例: 1. 发业务通知:实现短信,微信等不同类型的通知
 *       2. 记录日志: 将日志按不同的渠道记录，记录到文件,记录到db
 *
 *
 * @author kango2gler@gmail.com
 */
public class StrategyPatternTest {
    /**
     * 发通知类策略
     * Strategy 抽象策略接口，定义所有支持的算法的公共接口
     */
    interface Strategy{
        void notice();
    }

    /**
     * ConcreteStrategy 具体策略类，实现具体的算法
     * 微信通知策略
     */
    static class  WeChatNoticeStrategy implements Strategy{

        @Override
        public void notice() {
            System.out.println("发出微信通知！！！");
        }
    }
    /**
     * ConcreteStrategy 具体策略类，实现具体的算法
     * 短信通知策略
     */
    static class  SMSNoticeStrategy implements Strategy{

        @Override
        public void notice() {
            System.out.println("发出短信通知！！！");
        }
    }

    /**
     * 策略类上下文
     */
    static class StrategyContext{
        private Strategy strategy;

        public StrategyContext(Strategy strategy) {
            this.strategy = strategy;
        }
        void notice(){
            strategy.notice();
        }
    }


    static void main(){
        new StrategyContext(new WeChatNoticeStrategy()).notice();
        new StrategyContext(new SMSNoticeStrategy()).notice();
    }
    public static void main(String[] args) {
        main();
    }
}
