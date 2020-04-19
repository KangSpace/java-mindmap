package org.kangspace.common.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障
 * 2020/1/6 18:06
 *
 * @author kango2gler@gmail.com
 */
public class CyclicBarrierTest {

    static void main(){
        int i = 2;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(i, () -> {
            System.out.println("CyclicBarrier 所有线程结束，执行回调线程"+System.currentTimeMillis());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        new Thread(()->{
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "-CyclicBarrier 结束"+System.currentTimeMillis());
        }).start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("全部到达屏障...."+System.currentTimeMillis());
    }
    public static void main(String[] args) {
        main();
    }
}
