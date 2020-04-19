package org.kangspace.common.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2020/1/6 18:05
 *
 * @author kango2gler@gmail.com
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        main();
    }
    static void main(){
        String[] args = new String[]{};
        ReadLockService.main(args);
        WriteLockService.main(args);

    }

    /**
     * 读锁 readLock
     * 读读共享
     */
    static class ReadLockService{
        private ReentrantReadWriteLock readLock = new ReentrantReadWriteLock();
        public void read(){
            try {
                readLock.readLock().lock();
                System.out.println("获取读锁: "+Thread.currentThread().getName()+" "+ System.currentTimeMillis());
                Thread.sleep(1001L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.readLock().unlock();
            }
        }

        static class ThreadA extends Thread {
            ReadLockService service;

            public ThreadA(ReadLockService service,String name) {
                this.service = service;
                setName(name);
            }

            @Override
            public void run() {
                service.read();
            }
        }

        public static void main(String[] args) {
            ReadLockService service = new ReadLockService();
            ThreadA threadA = new ThreadA(service,"threadA");
            ThreadA threadA2 =new ThreadA(service,"threadA2");
            threadA.start();
            threadA2.start();
        }
    }

    /**
     * 写锁 writeLock
     * 写写互斥,写读互斥,读写互斥
     */
    static class WriteLockService{
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        public void read(){
            try {
                lock.readLock().lock();
                System.out.println("获取读锁: "+Thread.currentThread().getName()+" "+ System.currentTimeMillis());
                Thread.sleep(1001L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
        public void write(){
            try {
                lock.writeLock().lock();
                System.out.println("获取写锁: "+Thread.currentThread().getName()+" "+ System.currentTimeMillis());
                Thread.sleep(1001L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }

        static class ThreadA extends Thread {
            WriteLockService service;
            boolean read;

            public ThreadA(WriteLockService service,String name,boolean read) {
                this.service = service;
                this.read = read;
                setName(name);
            }

            @Override
            public void run() {
                if (read) {
                    service.read();
                }else {
                    service.write();
                }
            }
        }

        public static void main(String[] args) {
            WriteLockService service = new WriteLockService();
            ThreadA threadA = new WriteLockService.ThreadA(service,"threadA",true);
            ThreadA threadA2 =new WriteLockService.ThreadA(service,"threadA2",false);
            threadA.start();
            threadA2.start();
        }
    }


}
