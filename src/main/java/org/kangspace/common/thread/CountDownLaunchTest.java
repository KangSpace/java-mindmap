package org.kangspace.common.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 2020/1/6 18:06
 *
 * @author kango2gler@gmail.com
 */
public class CountDownLaunchTest {
    static void main(){
        int i = 1;
        CountDownLatch countDownLaunch = new CountDownLatch(i);
        new Thread(()->{
            try {
                Thread.sleep(5000L*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLaunch.countDown();
            System.out.println(Thread.currentThread().getName()+"-"+"CountDownLaunch");
        }).start();

        try {
            countDownLaunch.await();
            System.out.println(countDownLaunch.getCount());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) {
        main();
    }
}
