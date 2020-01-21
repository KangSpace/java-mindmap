package org.kangspace.common.designpattern.creational;

/**
 * 工厂方法:
 * 提供一个创建对象的接口，由子类决定实例化哪个类。FactoryMethod使一个类的实例化延迟到其子类。
 * 1. 普通工厂方法
 * 2. 多个工厂方法模式
 * 3. 静态工厂方法
 * 2020/1/6 18:05
 *
 * @author kangxuefeng@etiantian.com
 */
public class FactoryMethodTest {

    /**
     * Product,工厂方法所需创建的对象接口
     */
    interface Work{void doWork();}

    /**
     * 工厂方法所需创建的对象
     */
    static class StudentWork implements Work{
        @Override
        public void doWork(){
            System.out.println("学生Work做作业");
        }
    }

    /**
     * 工厂方法所需创建的对象
     */
    static class TeacherWork implements Work{
        @Override
        public void doWork(){
            System.out.println("教师Work批作业");
        }
    }

    /**
     * 工厂类
     */
    interface IWorkFactory{
        /**
         * 工厂方法
         * @return
         */
        Work getWork();
    }

    /**
     * 工厂方法子类
     */
    static class StudentWorkFactory implements IWorkFactory {
        @Override
        public Work getWork() {
            return new StudentWork();
        }
    }
    /**
     * 工厂方法子类
     */
    static class TeacherWorkFactory implements IWorkFactory {
        @Override
        public Work getWork() {
            return new TeacherWork();
        }
    }

    static void main(){
        Work studentWork = new StudentWorkFactory().getWork();
        studentWork.doWork();
        Work teacherWork = new TeacherWorkFactory().getWork();
        teacherWork.doWork();
    }
    public static void main(String[] args) {
        main();
    }
}
