package org.kangspace.common.algorithms.tests;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class OddEvenPrintTest {
    static AtomicInteger i = new AtomicInteger(0);

    static void main() {
        Lock logk1 = new ReentrantLock();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Condition ci = logk1.newCondition();
        Condition ci2 = logk1.newCondition();
        new Thread(() -> {
            while (i.get() < arr.length) {
                if (logk1.tryLock()) {
                    if (i.get() % 2 == 0) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ":" + arr[i.getAndIncrement()]+","+System.currentTimeMillis());
                            ci.signal();
                            ci2.await(2L, TimeUnit.SECONDS);
                            Thread.sleep(100L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                                logk1.unlock();
                        }
                    }
                }
            }
            System.out.println("end!!");
        }
        ).start();
        new Thread(() -> {
            while (i.get() < arr.length) {
                if (logk1.tryLock()) {
                    if (i.get() % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + arr[i.getAndIncrement()]+","+System.currentTimeMillis());
                        try {
                            ci2.signal();
                            ci.await(2L, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            logk1.unlock();
                        }
                    }
                }
            }
            System.out.println("end2!!");
        }).start();

    }

    public static void main(String[] args) {
        main();
    }
}
