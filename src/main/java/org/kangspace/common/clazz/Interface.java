package org.kangspace.common.clazz;

/**
 * 2019/11/29 9:39
 *
 * @author kango2gler@gmail.com
 */
@FunctionalInterface
public interface Interface {
    default void run() {
        System.out.println("Interface default run");
    }
    default void run2() {
        System.out.println("Interface default run");
    }
    void run1();
}
