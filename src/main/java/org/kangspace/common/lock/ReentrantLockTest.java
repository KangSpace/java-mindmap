package org.kangspace.common.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020/1/6 18:05
 *
 * @author kangxuefeng@etiantian.com
 */
public class ReentrantLockTest {
    /**
     *  AQS
     */
    static ReentrantLock noFairReentrantLock = new ReentrantLock();
    static ReentrantLock fairReentrantLock = new ReentrantLock(true);

    static void lock(){
        noFairReentrantLock.newCondition();
        //获取锁
        noFairReentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+" - noFairReentrantLock.lock()");
        //释放锁
        noFairReentrantLock.unlock();
    }
    public static void main(String[] args) {
        main();
    }


    private int start = 10;
    private int middle = 90;
    private int end = 200;

    private volatile int tmpAns1 = 0;
    private volatile int tmpAns2 = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private AtomicInteger count = new AtomicInteger(0);


    private int add(int i, int j) {
        try {
            lock.lock();
            int sum = 0;
            for (int tmp = i; tmp < j; tmp++) {
                sum += tmp;
            }
            return sum;
        } finally {
            atomic();
            lock.unlock();
        }
    }


    private int sum() throws InterruptedException {
        try {
            lock.lock();
            condition.await();
            return tmpAns1 + tmpAns2;
        } finally {
            lock.unlock();
        }
    }

    private void atomic() {
        if (2 == count.addAndGet(1)) {
            condition.signal();
        }
    }

    /**
     * Condtion一般是与Lock配套使用，应用在多线程协同工作的场景中；即一个线程的执行，期望另一个线程执行完毕之后才完成
     *
     * 针对这种方式，我们写个测试类，来实现累加，要求如下:
     *
     * 线程1实现 start-middle的累加；线程2实现middle-end的累加
     * 线程3实现线程1和线程2计算结果的相加
     */
    static void main(){
        ReentrantLockTest demo = new ReentrantLockTest();
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +
                    " : 开始执行");
            demo.tmpAns1 = demo.add(demo.start, demo.middle);
            System.out.println(Thread.currentThread().getName() +
                    " : calculate ans: " + demo.tmpAns1);
        }, "count1");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +
                    " : 开始执行");
            demo.tmpAns2 = demo.add(demo.middle, demo.end + 1);
            System.out.println(Thread.currentThread().getName() +
                    " : calculate ans: " + demo.tmpAns2);
        }, "count2");

        Thread thread3 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() +
                        " : 开始执行");
                int ans = demo.sum();
                System.out.println("the total result: " + ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "sum");


        thread3.start();
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
            System.out.println("执行完成后加锁");
            //若sum后不解锁，会死锁
//            demo.lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }
}
