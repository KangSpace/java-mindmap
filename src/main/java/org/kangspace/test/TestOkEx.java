package org.kangspace.test;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kango2gler@gmail.com
 * @since 2022/7/1
 */
public class TestOkEx {
    static AtomicInteger NUM = new AtomicInteger(0);

    /**
     * 2线程奇偶数交替打印
     * 1,2,3,4....50
     */
    public static void numberPrint(){
        int maxNum = 50;
        CountDownLatch latch = new CountDownLatch(2);
        ReentrantLock lock = new ReentrantLock();
        Condition jishuCondintion = lock.newCondition();
        Condition oushuCondintion = lock.newCondition();

        // 奇数线程
        Thread jishuThread = new Thread(()->{
            while(true) {
                if (lock.tryLock()) {
                    try {
                        int curr = NUM.getAndIncrement();
                        if (curr % 2 != 0) {
                            System.out.println(curr);
                            oushuCondintion.signal();
                            if (NUM.get() >= maxNum) {
                                latch.countDown();
                                return;
                            }
                            jishuCondintion.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        // 偶数线程
        Thread oushuThread = new Thread(()->{
            while(true) {
                if (lock.tryLock()) {
                    try {
                        int curr = NUM.getAndIncrement();
                        if (curr % 2 == 0) {
                            System.out.println(curr);
                            jishuCondintion.signal();
                            if (curr >= maxNum) {
                                latch.countDown();
                                return;
                            }
                            oushuCondintion.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        oushuThread.start();
        jishuThread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        numberPrint();
    }

}
