package org.kangspace.common.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 2020/1/6 18:05
 *
 * @author kangxuefeng@etiantian.com
 */
public class SemaphoreTest {
    private Semaphore smp = new Semaphore(3);
    private Random rnd = new Random();

    class TaskDemo implements Runnable{
        private String id;
        TaskDemo(String id){
            this.id = id;
        }
        @Override
        public void run(){
            try {
                smp.acquire();
                System.out.println("Thread " + id + " is working");
                Thread.sleep(rnd.nextInt(1000));
                smp.release();
                System.out.println("Thread " + id + " is over");
            } catch (InterruptedException e) {
            }
        }
    }

    static void main(){
        SemaphoreTest semaphoreDemo = new SemaphoreTest();
        //注意我创建的线程池类型，
        ExecutorService se = Executors.newCachedThreadPool();
        se.submit(semaphoreDemo.new TaskDemo("a"));
        se.submit(semaphoreDemo.new TaskDemo("b"));
        se.submit(semaphoreDemo.new TaskDemo("c"));
        se.submit(semaphoreDemo.new TaskDemo("d"));
        se.submit(semaphoreDemo.new TaskDemo("e"));
        se.submit(semaphoreDemo.new TaskDemo("f"));
        se.shutdown();
    }
    public static void main(String[] args) {
        main();
    }
}
