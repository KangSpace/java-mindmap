package org.kangspace.common.designpattern.behavioral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 访问者模式
 * 概述: 表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作
 * 适用性: 1. 一个对象结构包含很多类对象，它们有不同的接口，而你想对这些对象实施一些依赖于其具体类的操作
 * 2. 需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而你想避免让这些操作“污染”这个对象
 * Visitor使得你可以将相关操作集中起来定义在一个类中。
 * 当改对象结构被很对应用共享时，用Visitor模式让每个应用仅包含需要用到的操作。
 * 3. 定义对象结构的类很少改变，但经常需要在此结构上定义新的操作。
 * 改变对象结构类需要重定义对所有访问者的接口，这可能需要很大的代价。
 * 如果对象结构类经常改变，那么可能还是在这些类中定义这些操作较好。
 * 参与者:
 * 1. Visitor
 * 为该对象结构中ConcreteElement的每一个类声明一个Visit操作。
 * 该操作的名字和特征标识了发送Visit请求给该访问者的那个类。
 * 这使得访问者可以确定正在被访问元素的具体的类。
 * 这样访问者就可以通过该元素的特定接口直接访问它。
 * 2. ConcreteVisitor
 * 实现每个由Visitor声明的操作。
 * 每个操作实现本算法的一部分，而该算法片段仍是对应于结构中对象的类。
 * ConcreteVisitor 为该算法提供了上下文并存储它的局部状态。
 * 这一状态常常在遍历改结构的过程中积累结果。
 * 3. Element
 * 定义一个Accept 操作，它以一个访问者为参数。
 * 4. ConcreteElement
 * 实现Accept操作，该操作以一个访问者为参数。
 * 5. ObjectStructure
 * 对象结构，能枚举它的元素。
 * 可以提供一个高层的结构以允许访问者访问它的元素。
 * 可以是一个复合或者一个集合，如一个列表或一个无序集合。
 * <p>
 * 样例(应用场景):
 *
 * @author kango2gler@gmail.com
 */
public class VisitorPatternTest {
    /**
     * 抽象访问者，为该对象结构中ConcreteElement的每个类声明一个Visite的操作
     */
    interface Visitor {
        void visitFloat(FloatElement floatElement);
        void visitString(StringElement stringElement);
        void visitCollection(Collection<Element> elements);
    }

    static class ConcreteVisitor implements Visitor{

        @Override
        public void visitFloat(FloatElement floatElement) {
            System.out.println("visitFloat:"+floatElement.getFlo());
        }

        @Override
        public void visitString(StringElement stringElement) {
            System.out.println("visitString:"+stringElement.getStr());
        }

        @Override
        public void visitCollection(Collection<Element> elements) {
            elements.forEach(t->{
                t.accept(this);
            });
        }
    }
    static class StringConcreteVisitor implements Visitor{

        @Override
        public void visitFloat(FloatElement floatElement) {
        }

        @Override
        public void visitString(StringElement stringElement) {
            System.out.println("StringConcreteVisitor:"+stringElement.getStr());
        }

        @Override
        public void visitCollection(Collection<Element> elements) {
            elements.forEach(t->{
                t.accept(this);
            });
        }
    }

    /**
     * 定义一个Accept操作，以一个访问者为参数
     */
    interface Element {
        void accept(Visitor visitor);
    }

    /**
     * 具体Element, 实现Element定义的accept操作
     */
    static class FloatElement implements Element {
        private Float flo;

        public FloatElement(Float flo) {
            this.flo = flo;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitFloat(this);
        }

        public Float getFlo() {
            return flo;
        }

        public void setFlo(Float flo) {
            this.flo = flo;
        }
    }
    static class StringElement implements Element {
        private String str;

        public StringElement(String str) {
            this.str = str;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitString(this);
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    static void main() {
        System.out.println("===================访问者1");
        Visitor visitor = new ConcreteVisitor();
        StringElement stringElement = new StringElement("StirngElement对象");
        //一个对象可以接收不同的Visitor以实现不同的业务处理
        stringElement.accept(visitor);
        System.out.println("===================访问者2");
        stringElement.accept(new StringConcreteVisitor());

        System.out.println("===================访问者访问元素集合");
        List<Element> result = new ArrayList();
        result.add(new StringElement("遍历String1"));
        result.add(new StringElement("遍历String2"));
        result.add(new FloatElement(0.1F));
        result.add(new FloatElement(0.1_1F));
        visitor.visitCollection(result);

    }

    public static void main(String[] args) {
        main();
    }
}
