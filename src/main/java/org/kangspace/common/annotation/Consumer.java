package org.kangspace.common.annotation;


import static java.lang.System.out;

/**
 * 2019/11/29 9:50
 *
 * @author kango2gler@gmail.com
 */
@FunctionalInterface
public interface Consumer<T> {
    void run(T t);
    default void runDefault(){
        out.println("runDefault");
    }

    static void main(String[] args) {

    }

    class ImplConsumer<L> {
        Consumer<L> run() {
            return (L t) -> {
                out.println(t);
            };
        }
    }
}
