package org.kangspace.common.thread;

/**
 * 2019/12/17 17:50
 *
 * @author kango2gler@gmail.com
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("start");
        Thread thread= new Thread(){
            @Override
            public void run() {
                while(!this.isInterrupted()){
                    try {
                        sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("xx");
                }
                System.out.println("Thread.interrupted()："+Thread.interrupted());
                System.out.println("Thread.interrupted()："+Thread.interrupted());
                System.out.println("thread End");
            }
        };
        thread.start();
        thread.interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(thread.isInterrupted());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
