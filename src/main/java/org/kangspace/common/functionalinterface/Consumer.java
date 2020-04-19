package org.kangspace.common.functionalinterface;

/**
 * 2019/12/9 18:44
 *
 * @author kango2gler@gmail.com
 */
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default void defaultFn(){
        System.out.println("Consumer.defaultFn() run");
        return ;
    }

    static void staticFn(){
        System.out.println("Consumer.staticFn() run");
        return ;
    }
}
