package org.kangspace.common.jdk18;

import java.util.Optional;

/**
 * 2019/12/11 18:14
 *
 * @author kango2gler@gmail.com
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional empty = Optional.empty();
//        System.out.println(empty.get());
        System.out.println(empty.orElse("Or"));

        Optional of = Optional.of("null");
        Optional ofNullable = Optional.ofNullable(null);
        System.out.println(of.get());
        System.out.println(of.isPresent());
        of.ifPresent(t-> System.out.println(t));
        System.out.println("orElseGet");
        System.out.println(of.orElseGet(OptionalTest::run));
        System.out.println("orElse");
        System.out.println(of.orElse(run()));
        try {
            of.orElseThrow(RuntimeException::new);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(of.map(t -> t).orElseGet(()->"t"));
        System.out.println("flatMap:"+of.flatMap(t -> Optional.empty()));
        of.filter(t -> t != null).orElse("null");

    }
    public static Object run(){
        System.out.println("run");
        return 1;
    }
}
