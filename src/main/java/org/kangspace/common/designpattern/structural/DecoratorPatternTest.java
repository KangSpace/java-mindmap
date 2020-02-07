package org.kangspace.common.designpattern.structural;

/**
 * 2020/1/6 18:05
 * 装饰模式
 * 概述: 动态的给对象添加一些额外的职责。装饰模式比子类更加灵活
 * 适用性:
 * 1. 在不影响其他对象的情况下，动态，透明地给对象添加额外的职责
 * 2. 处理那些可以撤销的职责
 * 3. 当不能对对象生成子类扩充方法时
 * 样例:
 * 饮料示例
 * <p>
 * (实现: 将被装饰对象类作为装饰对象的属性，来调用被装饰对象的方法,同时 重写被装饰对象的方法:
 * 目的: 利用现有对象或接口，并增强该对象或接口
 * 装饰者与被装饰者必须拥有同一个超类或接口)
 *
 * @author kangxuefeng@etiantian.com
 */
public class DecoratorPatternTest {

    /**
     * 饮料接口
     */
    static abstract class YinLiao {
        String desc = "普通水";

        String getDescription() {
            return desc;
        }

        abstract double cost();
    }

    /**
     * 饮料的装饰者
     */
    static abstract class YinLiaoDecorator extends YinLiao {
        @Override
        public abstract String getDescription();
    }

    /**
     * 碳酸饮料
     */
    static class TanSuanYinLiao extends YinLiao {
        public TanSuanYinLiao() {
            desc = "普通碳酸饮料";
        }

        @Override
        double cost() {
            return 0;
        }
    }

    /**
     * 可乐饮料
     */
    static class KeLeYinLiao extends YinLiao {
        public KeLeYinLiao() {
            desc = "可乐";
        }

        @Override
        double cost() {
            return 1;
        }
    }

    /**
     * 雪碧: 碳酸饮料具体装饰者
     */
    static class XueBi extends YinLiaoDecorator {
        YinLiao decorated;

        public XueBi(YinLiao decorated) {
            this.decorated = decorated;
            desc = "雪碧";
        }

        @Override
        public String getDescription() {
            return decorated.getDescription() + ": " + this.desc;
        }

        @Override
        double cost() {
            return decorated.cost() + 1;
        }
    }

    /**
     * 美年达: 碳酸饮料具体装饰者
     */
    static class MeiNianDa extends YinLiaoDecorator {
        YinLiao decorated;

        public MeiNianDa(YinLiao decorated) {
            this.decorated = decorated;
            desc = "美年达";
        }

        @Override
        public String getDescription() {
            return decorated.getDescription() + ": " + this.desc;
        }

        @Override
        double cost() {
            return decorated.cost() + 1.5;
        }
    }


    static void main() {
        //创建一个碳酸饮料
        YinLiao yinLiao = new TanSuanYinLiao();
        System.out.println(yinLiao.getDescription() + "," + yinLiao.cost() + "元");
        XueBi xueBi = new XueBi(yinLiao);
        System.out.println(xueBi.getDescription() + "," + xueBi.cost() + "元");
        MeiNianDa meiNianDa = new MeiNianDa(yinLiao);
        System.out.println(meiNianDa.getDescription() + "," + meiNianDa.cost() + "元");

    }

    public static void main(String[] args) {
        main();
    }
}
