package org.kangspace.common.designpattern.creational;

/**
 * 抽象工厂:
 * 提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 *
 *
 * 2020/1/6 18:05
 *
 * @author kangxuefeng@etiantian.com
 */
public class AbstractFactoryTest {
    /**
     * Product: 桌子
     */
    interface Desk{
        String getDeskType();
    }

    /**
     * Product具体实现: 木桌
     */
    static class WoodenDesk implements Desk{
        private String deskType = "木桌";
        @Override
        public String getDeskType() {
            return deskType;
        }
    }

   /**
     * Product具体实现: 塑料桌
     */
    static class PlasticDesk implements Desk{
        private String deskType = "塑料桌";
        @Override
        public String getDeskType() {
            return deskType;
        }
    }

    /**
     * Product: 椅子
     */
    interface Chair{
        String getChairType();
    }

    /**
     * Product具体实现: 木椅子
     */
    static class WoodenChair implements Chair{
        private String chairType = "木椅子";
        @Override
        public String getChairType() {
            return chairType;
        }
    }

    /**
     * Product具体实现: 塑料椅子
     */
    static class PlasticChair implements Chair{
        private String chairType = "塑料椅子";
        @Override
        public String getChairType() {
            return chairType;
        }
    }



    /**
     * 家具抽象工厂接口
     * 创建多个相关依赖对象的接口
     */
    interface FurnitureFactory{
        Desk buildDesk();
        Chair buildChair();
    }

    /**
     * 家具抽象工厂接口具体实现: 塑料家具
     */
    static class PlasticFurnitureFactory implements FurnitureFactory{

        @Override
        public Desk buildDesk() {
            return new PlasticDesk();
        }

        @Override
        public Chair buildChair() {
            return new PlasticChair();
        }
    }
    /**
     * 家具抽象工厂接口具体实现: 木质家具
     */
    static class WoodenFurnitureFactory implements FurnitureFactory{

        @Override
        public Desk buildDesk() {
            return new WoodenDesk();
        }

        @Override
        public Chair buildChair() {
            return new WoodenChair();
        }
    }

    static void main(){
        FurnitureFactory woodenFurniture = new WoodenFurnitureFactory();
        FurnitureFactory  plasticFurniture = new PlasticFurnitureFactory();
        System.out.println(woodenFurniture.buildChair().getChairType());
        System.out.println(plasticFurniture.buildChair().getChairType());

    }
    public static void main(String[] args) {
        main();
    }
}
