package org.kangspace.common.designpattern.creational;

/**
 * 原型模式:
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * 需要实现Cloneable接口，覆写clone方法
 *
 * 2020/1/6 18:05
 *
 * @author kangxuefeng@etiantian.com
 */
public class PrototypePatternTest {
    static void main(){
        PrototypeObject obj = new PrototypeObject(1,"FirstObj");
        System.out.println("obj:"+obj);
        try {
            PrototypeObject cloneObj = obj.clone();
            System.out.println("cloneObj:"+cloneObj);
            cloneObj.setName("CloneObj");
            cloneObj.setNum1(2);
            System.out.println("cloneObjUpdate:"+cloneObj);
            System.out.println("obj:"+obj);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        main();
    }

    /**
     * 原型对象
     */
    static class PrototypeObject implements Cloneable{
        int num1;
        String name;


        @Override
        public PrototypeObject clone() throws CloneNotSupportedException {
            PrototypeObject clone = (PrototypeObject) super.clone();
            return clone;
        }

        public PrototypeObject() {
        }

        public PrototypeObject(int num1, String name) {
            this.num1 = num1;
            this.name = name;
        }

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "PrototypeObject{" +
                    "num1=" + num1 +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
