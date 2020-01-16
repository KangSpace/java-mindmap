package org.kangspace.common.jdk18;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author kango2gler@gmail.com
 * @desc
 * @date 2019/12/11 22:28
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream.of("a");
        new BufferedReader(null).lines();
        IntStream.range(0, 1);
//        Files.walk();
//        Stream.generate(<Supplier>Obj);
        StreamSupport.stream(new Spliterator<Object>() {
            @Override
            public boolean tryAdvance(Consumer<? super Object> action) {
                return false;
            }

            @Override
            public Spliterator<Object> trySplit() {
                return null;
            }

            @Override
            public long estimateSize() {
                return 0;
            }

            @Override
            public int characteristics() {
                return 0;
            }
        }, false);
        IntSummaryStatistics intSummaryStatistics = IntStream.builder().build().flatMap((e) -> IntStream.builder().build()).summaryStatistics();
        Comparator.comparingInt((a) -> Integer.valueOf(a.toString()));
        new ArrayList<>().stream().map(t->t).collect(Collectors.toList());
    }
}
