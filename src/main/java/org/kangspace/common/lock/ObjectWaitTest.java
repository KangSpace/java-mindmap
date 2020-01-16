package org.kangspace.common.lock;

/**
 * Object.wait(),notify()
 * 1. wait() ,拥有当前锁的对象释放锁，线程进入等待状态
 * 2. notify/notifyAll() 执行后会唤醒处于等待状态线程获取线程锁、只是notify()只会随机唤醒其中之一获取线程锁，notifyAll() 会唤醒所有处于等待状态的线程抢夺线程锁。
 * 2020/1/10 17:21
 *
 * @author kangxuefeng@etiantian.com
 */
public class ObjectWaitTest {
    static void main(){
        Short a = null;
        if( 11 == a){
            System.out.println(true);
        }
        Object lock = new Object();
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                methodClass.customer(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
        t2.start();
//        t3.start();

    }
    public static void main(String[] args) {
        main();
    }


    static class MethodClass {
        // 定义生产最大量
        private final int MAX_COUNT = 20;

        int productCount = 0;

        public void product(Object lock) throws InterruptedException {
            synchronized(lock) {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
                    Thread.sleep(10);
                    if (productCount >= MAX_COUNT) {
                        System.out.println("货舱已满,,.不必再生产");

                        lock.wait();
                    } else {
                        productCount++;
                    }

                    lock.notifyAll();
                }
            }
        }

        public void customer(Object lock) throws InterruptedException {
            synchronized(lock) {
                while (true) {
//                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
                    if (productCount <= 0) {
                        Thread.sleep(1000);
                        System.out.println("货舱已无货...无法消费");
                        lock.wait();
                    } else {
                        productCount--;
                    }

                    lock.notifyAll();
                }
            }
        }
    }
}
