package org.kangspace.common.jdk18;

import org.apache.commons.lang.time.StopWatch;

import java.io.BufferedReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Stream流测试
 * 1. parallelStream() 使用ForkJoinPool 线程池生成线程,同时也会使用main线程
 * ForkJoinPool.commonPool 默认线程数:Runtime.getRuntime().availableProcessors() - 1
 * 2. forEachOrdered 与 parallelStream() 同时使用时,不会并行处理, forEachOrdered只会在一个线程下运行(在ForkJoinPool.commonPool的一个线程下运行)
 * @author kango2gler@gmail.com
 * @desc
 * @date 2019/12/11 22:28
 */
public class StreamTest {
    public static void main(String[] args) {
//        normalTest();
        collectionStreamTest();
    }

    public static void normalTest() {
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
        new ArrayList<>().stream().map(t -> t).collect(Collectors.toList());
    }

    /**
     * 集合Stream测试
     */
    public static void collectionStreamTest() {
        List<String> list = new ArrayList<>();
        int count = 1000000;
        for (int i = 0; i < count; i++) {
            list.add("iota" + i);
        }
        System.out.println("list:" + list);
        System.out.println("串行Stream:");
        Set<String> threadNames = new HashSet<>();
        StopWatch sw = new StopWatch();
        sw.start();
        list.stream().forEach(t -> {
            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + ", t:" + t);
            threadNames.add(threadName);
        });
        sw.stop();
        System.out.println("串行Stream,耗时:" + sw.getTime() + ", 线程列表:" + threadNames);


        sw.reset();
        System.out.println("串行Stream(顺序Foreach):");
        sw.start();
        threadNames.clear();
        // forEachOrdered 与 parallelStream() 同时使用时,不会并行处理, forEachOrdered只会在一个线程下运行
        list.stream().forEachOrdered(t -> {
            String threadName = Thread.currentThread().getName();
            threadNames.add(threadName);
        });
        sw.stop();
        System.out.println("串行Stream(顺序Foreach),耗时:" + sw.getTime() + ", 线程列表:" + threadNames);

        sw.reset();
        System.out.println("并行Stream:");
        sw.start();
        threadNames.clear();
        // parallelStream() 使用ForkJoinPool 线程池生成线程,同时也会使用main线程
        // ForkJoinPool.commonPool 默认线程数:Runtime.getRuntime().availableProcessors() - 1
        list.parallelStream().forEach(t -> {
            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + ", t:" + t);
            threadNames.add(threadName);
        });
        sw.stop();
        System.out.println("并行Stream,耗时:" + sw.getTime() + ", 线程列表:" + threadNames);

        sw.reset();
        System.out.println("并行Stream(顺序Foreach):");
        sw.start();
        threadNames.clear();
        // forEachOrdered 与 parallelStream() 同时使用时,不会并行处理, forEachOrdered只会在一个线程下运行
        list.parallelStream().forEachOrdered(t -> {
            String threadName = Thread.currentThread().getName();
            threadNames.add(threadName);
        });
        sw.stop();
        System.out.println("并行Stream(顺序Foreach),耗时:" + sw.getTime() + ", 线程列表:" + threadNames);

        System.out.println("系统内核数:"+Runtime.getRuntime().availableProcessors());
    }
}
