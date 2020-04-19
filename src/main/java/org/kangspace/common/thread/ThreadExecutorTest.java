package org.kangspace.common.thread;

import java.util.concurrent.*;

/**
 * 2020/1/6 18:05
 *
 * @author kango2gler@gmail.com
 */
public class ThreadExecutorTest {
    static void newFiexedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        fixedThreadPool.execute(()->{
            try {
                System.out.println("Thread1 run");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fixedThreadPool.execute(()->{
            try {
                System.out.println("Thread2 run");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        try {
////            fixedThreadPool.awaitTermination(50000L,TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(fixedThreadPool.isShutdown()) {
            fixedThreadPool.shutdown();
        }
//        System.out.println("Thread end");
    }
    static void newCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i <= 10000;i++) {
            int finalI = i;
            pool.execute(()->{
                try {
                    System.out.println("Thread"+ finalI +" run");
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
    static void diyThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,1,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(1));
        executor.execute(()->{
            try {
                System.out.println("Thread1 run");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        executor.execute(()->{
            try {
                System.out.println("Thread2 run");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        try {
////            fixedThreadPool.awaitTermination(50000L,TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(executor.isShutdown()) {
            executor.shutdown();
        }
//        System.out.println("Thread end");
    }
    static void main(){
//        diyThreadPool();
//        newFiexedThreadPool();
        newCachedThreadPool();
    }
    static void main1(){
        // 默认拒绝处理器RejectedExecutionHandler:
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        //最大线程数Integer.MAX_MAX_VALUE , keepAliveTime: 60s
        // SynchronousQueue 没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        //LinkedBlockingQueue 队列最大长度:Integer.MAX_MAX_VALUE 是一个无界缓冲等待队列
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        //创建一个定长线程池，支持定时及周期性任务执行。
        //最大线程数:Integer.MAX_MAX_VALUE
        //DelayedWorkQueue
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(100);
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        //LinkedBlockingQueue 队列最大长度:Integer.MAX_MAX_VALUE
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,1,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(10));
        try {
            cachedThreadPool.submit(()->{}).get();
            cachedThreadPool.execute(()->{});
            cachedThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        main();
    }
}
