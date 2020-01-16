package org.kangspace.common.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * 2020/1/6 18:05
 *
 * @author kangxuefeng@etiantian.com
 */
public class LockSupportTest {
    static void main() {
        //LockSupport.pack() 不释放锁,同Thread.sleep()
        Thread parkThread = new InnerThread(true, "parkThread",null);
        parkThread.start();
        //可以响应中断
//        parkThread.interrupt();
        new InnerThread(false, "unparkThread",parkThread).start();

    }

    static class InnerThread extends Thread{
        boolean isPark;
        Thread thread;

        public InnerThread(boolean isPark,String threadName,Thread thread) {
            setName(threadName);
            this.isPark = isPark;
            this.thread = thread;
        }

        @Override
        public void run() {
            if(isPark){
                System.out.println("LockSupport.park():"+this.getId()+"-"+this.getName()+"-"+ System.currentTimeMillis());
                LockSupport.park();
                System.out.println("LockSupport.park() next~~~"+"-"+ System.currentTimeMillis());
            }else{
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("LockSupport.unpark():"+this.getId()+"-"+thread.getName()+"-"+ System.currentTimeMillis());
                LockSupport.unpark(thread);
                System.out.println("LockSupport.unpark() next~~~"+"-"+ System.currentTimeMillis());
            }
        }
    }


    public static void main(String[] args) {
        main();
    }
}
