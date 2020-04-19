package org.kangspace.common.designpattern.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * 2020/1/6 18:05
 * 享元模式
 * 概述: 运用共享技术来支撑大量细粒度对象
 *
 * 样例:
 *      JVM 字符串常量池
 * @author kango2gler@gmail.com
 */
public class FlyWeightPatternTest {
    interface IFlyweight{
        void action(int i);
    }

    static class Flyweight implements IFlyweight {
        @Override
        public void action(int i) {
            System.out.println("参数："+i);
        }
    }
    
    static class FlyweightFactory{
        static Map<Integer,IFlyweight> flyweightCache = new HashMap<>();

        public FlyweightFactory(Integer arg) {
            putFlyweight(arg);
        }

        private static IFlyweight putFlyweight(Integer arg) {
            IFlyweight fw = new Flyweight();
            flyweightCache.put(arg, fw);
            return fw;
        }

        public static IFlyweight getFlyweight(Integer arg){
            IFlyweight fw = flyweightCache.get(arg);
            if (fw == null) {
                fw = putFlyweight(arg);
            }
            return fw;
        }
        public int getSize() {
            return flyweightCache.size();
        }
    }

    static void main(){
        IFlyweight fw1 = FlyweightFactory.getFlyweight(1);
        IFlyweight fw2 = FlyweightFactory.getFlyweight(2);
        IFlyweight fw1_ = FlyweightFactory.getFlyweight(1);
        IFlyweight fw4 = FlyweightFactory.getFlyweight(4);
        fw1.action(1);
        fw2.action(2);
        fw1_.action(3);
        fw2.action(4);
        System.out.println("fw1 == fw1_:"+(fw1 == fw1_));
    }
    public static void main(String[] args) {
        main();
    }
}
