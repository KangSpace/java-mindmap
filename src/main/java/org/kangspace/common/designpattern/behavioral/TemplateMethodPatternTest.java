package org.kangspace.common.designpattern.behavioral;

/**
 * 模版方法
 * 概述:  定义一个操作中的算法的骨架，而将一些步骤延迟到子类中
 *        TemplateMethod 使得子类可以不改变一个算法的结构即可重新定义该算法的某些特定步骤
 *        模板方法模式是一种基于继承的代码复用技术
 * 适用性: 1. 一次性实现一个算法的不变部分，并将可变的部分留给子类来实现
 *         2. 各子类中公共的行为应被提起出来并集中到一个公共父类中以避免代码重复，
 *            首先识别现有代码的不同之处，并将不同之处分离为新的操作。
 *            最后用一个调用这些新的操作的模版方法来替换这些不同的代码。
 *         3. 控制子类扩展
 *
 *
 * 参与者:
 *      1. AbstractClass 在抽象类中定义了一系列基本操作(PrimitiveOperations)，这些基本操作可以是具体的，也可以是抽象的，
 *       每一个基本操作对应算法的一个步骤，在其子类中可以重定义或实现这些步骤。
 *       同时，在抽象类中实现了一个模板方法(Template Method)，用于定义一个算法的框架，模板方法不仅可以调用在抽象类中实现的基本方法，
 *       也可以调用在抽象类的子类中实现的基本方法，还可以调用其他对象中的方法。
 *       2. ConcreteClass 是抽象类的子类，用于实现在父类中声明的抽象基本操作以完成子类特定算法的步骤，也可以覆盖在父类中已经实现的具体基本操作。
 * 样例:
 *      Servlet: Servlet(Server Applet)是Java Servlet的简称，用Java编写的服务器端程序，主要功能在于交互式地浏览和修改数据，生成动态Web内容。在每一个 Servlet 都必须要实现 Servlet 接口，GenericServlet 是个通用的、不特定于任何协议的Servlet，它实现了 Servlet 接口，而 HttpServlet 继承于 GenericServlet，实现了 Servlet 接口，为 Servlet 接口提供了处理HTTP协议的通用实现，所以我们定义的 Servlet 只需要继承 HttpServlet 即可。
 * @author kangxuefeng@etiantian.com
 */
public class TemplateMethodPatternTest {
    /**
     * 模版方法抽象类
     * 定义一系列基础操作，这些基础操作可以是具体的也可以是抽象的。
     * 同时在抽象类中实现了一个模版方法,用于定义一个算法的框架。
     */
    static abstract class TemplateMethodAbstractClass{
        /**
         * 基本操作，抽象的
         */
        public abstract void print();

        /**
         * 模版方法
         */
        public void draw(){
            System.out.println("开始画画：");
            print();
            printEnd();
        }

        /**
         * 基本操作，具体的
         */
        public void printEnd(){
            System.out.println("画画结束");
        }
    }

    /**
     * 具体抽象子类，用于实现父类中声明的抽象基本操作已完成子类特定算法的步骤，也可以覆盖父类中已经实现的基本操作
     */
    static class PrintLineConcreteClass extends TemplateMethodAbstractClass{

        @Override
        public void print() {
            System.out.println("画直线");
        }
    }
    /**
     * 具体抽象子类，用于实现父类中声明的抽象基本操作已完成子类特定算法的步骤，也可以覆盖父类中已经实现的基本操作
     */
    static class PrintCicleConcreteClass extends TemplateMethodAbstractClass{

        @Override
        public void print() {
            System.out.println("画圆");
        }

        @Override
        public void printEnd() {
            System.out.println("画圆结束，圆的半径为5");
        }
    }


    static void main(){
        System.out.println("具体子类1");
        new PrintLineConcreteClass().draw();
        System.out.println("具体子类2");
        new PrintCicleConcreteClass().draw();
    }
    public static void main(String[] args) {
        main();
    }
}
