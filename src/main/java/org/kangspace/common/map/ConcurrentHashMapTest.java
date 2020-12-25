package org.kangspace.common.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * java.util.concurrent.ConcurrentHashMap 测试
 */
public class ConcurrentHashMapTest {
    static void main(){
        try {
            ConcurrentHashMap map = new ConcurrentHashMap();

            ForkJoinTask task1 = new RecursiveTask() {
                @Override
                protected Object compute() {
                    for (int i = 0; i < 10; i++) {
                        map.put(i, i);
                    }
                    return map;
                }
            };
            ForkJoinTask task2 = new RecursiveTask() {
                @Override
                protected Object compute() {
                    for (int i = 10; i < 20; i++) {
                        map.put(i, i);
                    }
                    return map;
                }
            };
            task2.fork();
            task1.fork();
            task1.join();
            task2.join();

            System.out.println(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        main();
    }
}
