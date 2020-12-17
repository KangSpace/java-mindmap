package org.kangspace.common.thread;

import java.util.Date;

/**
 * <pre>
 * 父线程或主线程结束后子线程继续运行
 * 父线程subThread1结束,子线程subThread2继续运行,同时主线程也结束
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/11/5 15:23
 */
public class SubThreadRunningOnParentOrMainEnd {
    static void main() {
        Thread thread = Thread.currentThread();
        System.out.println("main thread:" + thread);
        ThreadGroup tg = new ThreadGroup("SubThreadRunningOnParentEnd-SubThreadGroup");
        Thread subThread1 = new Thread(tg, () -> {
            System.out.println(new Date()+"subThread1 running");
            new Thread(tg, () -> {
                System.out.println(new Date()+"subThread2 running");
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    System.out.println("main thread isAlive:"+thread.isAlive());
//                    printThreadList(tg);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"subThread2").start();
        },"subThread1");
        subThread1.start();
        printThreadList(tg);
        try {
            Thread.sleep(5000L);
            subThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");
    }

    public static void printThreadList(ThreadGroup tg){
        System.out.println("thread list:");
        tg.list();
        System.out.println();
    }

    public static void main(String[] args) {
        main();
    }
}
