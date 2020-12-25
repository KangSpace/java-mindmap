package org.kangspace.common.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * <pre>
 *
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/12/15 17:40
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        System.out.println("start");
        ForkJoinPool pool = new ForkJoinPool(2);
        ForkJoinTask task1 = pool.submit(new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.println("不返回结果的任务:"+this.getClass().getName());
            }
        });
        ForkJoinTask task2 = pool.submit(new RecursiveTask<Object>() {
            @Override
            protected Object compute() {
                System.out.println("返回结果的任务:"+this.getClass().getName());
                return new Integer(1);
            }
        });
//        task2.get()
        task1.fork();
        task2.fork();
        task1.join();
        task2.join();
        System.out.println(Thread.interrupted());

    }
}
