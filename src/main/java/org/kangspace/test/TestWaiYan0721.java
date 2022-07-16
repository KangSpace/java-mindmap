package org.kangspace.test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.
 * 给定以下代码：
 * public class Foo {
 * public Foo() { ... }
 * public void first() { ... }
 * public void second() { ... }
 * public void third() { ... }
 * }
 * 同一个Foo实例会被传入 3 个不同的线程。threadA会调用first，threadB会调用second，
 * threadC会调用third，三个线程同时启动并运行
 * 请修改Foo类，确保first会在second之前调用，second会在third之前调用
 * 2.
 * 给定两个排序后的整型数组A和B，其中A的末端有足够的空间容纳B。编写一个方法，
 * 将B合并入A并排序
 * @author kango2gler@gmail.com
 * @since 2022/7/12
 */
public class TestWaiYan0721 {

    public static class Foo{
        AtomicInteger flag;
        public Foo(AtomicInteger flag){
            this.flag = flag;
        }
        public boolean first(){
            if(flag.get() == 1) {
                System.out.println("first: "+ Thread.currentThread().getName());
                flag.addAndGet(1);
                return true;
            }
            return false;
        }
        public boolean second(){
            if(flag.get() == 2) {
                System.out.println("second:"+ Thread.currentThread().getName());
                flag.addAndGet(1);
                return true;
            }
            return false;
        }
        public boolean third(){
            if(flag.get() == 3) {
                System.out.println("third:"+ Thread.currentThread().getName());
                flag.addAndGet(1);
                return true;
            }
            return false;
        }
    }

    /**
     *
     */
    public static void test1() {
        System.out.println("start!");
        AtomicInteger flag = new AtomicInteger(1);
        Foo foo = new Foo(flag);
        CountDownLatch latch = new CountDownLatch(3);
        Thread thread1 = new Thread(()->{
            while (true) {
                if(foo.first()){
                    latch.countDown();
                    return;
                }
            }


        },"thread1");
        Thread thread2 = new Thread(()->{
            while (true) {
                if (foo.second()) {
                    latch.countDown();
                    return;
                }
            }
        },"thread2");
        Thread thread3 = new Thread(()->{
            while (true) {
                if (foo.third()) {
                    latch.countDown();
                    return;
                }
            }
        },"thread3");
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }

    public static void test2(){
        System.out.println("start!");
        int[] arrayA = new int[]{1,3,5,7,9,12,13,14};
        int[] arrayB = new int[]{2,4,6,8,10,11};
        int arrayBLen = arrayB.length;
        int arrayALen = arrayA.length;

        int[] temp = new int[arrayALen+arrayBLen];
        int i = 0,j=0, ti=0;
        for (; i <arrayALen && j<arrayBLen ; ti++) {
            if (arrayA[i] < arrayB[j]) {
                temp[ti] = arrayA[i];
                i++;
            }else{
                temp[ti] = arrayB[j];
                j++;
            }
        }
        while (i < arrayALen) {
            temp[ti++] = arrayA[i++];
        }
        while (j < arrayBLen) {
            temp[ti++] = arrayB[j++];
        }
        arrayA = temp;
        System.out.println(Arrays.toString(arrayA));
        System.out.println("end!");
    }

    public static void main(String[] args){
//        test1();
        test2();
    }



}
