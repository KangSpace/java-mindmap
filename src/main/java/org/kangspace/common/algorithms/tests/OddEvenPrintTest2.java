package org.kangspace.common.algorithms.tests;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2个线程顺序打印奇偶数
 * 1. AtomicInteger + synchronized
 * 2. syncroized, lock.notify lock.wait
 * 3. ReentainLock.condition condition.await() condition.signal
 */
public class OddEvenPrintTest2 {
    static AtomicInteger ai = new AtomicInteger(0);
    public static Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    //
    public synchronized static void print() {
        System.out.println(Thread.currentThread().getName()+": "+arr[ai.getAndIncrement()]);
    }
    static void main() {
        System.out.println("hello ");
        new Thread(()->{
            while (ai.get() < arr.length) {
                if (ai.get() % 2 == 0) {
//                    System.out.println("p1:"+ai.get());
                    print();
                }else{
                    System.out.println("1");
                }
            }
        },"Thread0").start();
        new Thread(()->{
            while (ai.get() < arr.length) {
                if (ai.get() % 2 == 1) {
//                    System.out.println("p2:"+ai.get());
                    print();
//                    try {
//                        Thread.sleep(100L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }else{
                    System.out.println("2");
                }
            }
        },"Thread1").start();


    }

    public static void main(String[] args) {
        main();
    }
}
