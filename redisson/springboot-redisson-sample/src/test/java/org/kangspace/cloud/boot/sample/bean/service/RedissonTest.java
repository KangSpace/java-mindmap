package org.kangspace.cloud.boot.sample.bean.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Redisson 测试 <br/>
 * doc: https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8 <br/>
 * 配置: <br/>
 * RedissonConfig: {@link org.kangspace.cloud.boot.sample.config.RedissonClientConfig} <br/>
 * @author kango2gler@gmail.com
 * @since 2022/3/21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonTest {
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private RedissonClient otherRedissonClient;

    /**
     * Redisson 闭锁（CountDownLatch）测试<br/>
     *
     */
    @SneakyThrows
    @Test
    public void redissonCountDownLatchTest() {
        log.info("redissonCountDownLatchTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_COUNTDOWN_LATCH_LOCK_KEY";
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(lockKey);
        Integer defaultCount = 2;
        boolean isSet = countDownLatch.trySetCount(defaultCount);
        log.info("设置count: {}, 设置结果: {}", defaultCount, isSet);
        for (int i = 0; i <= defaultCount; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                }
                countDownLatch.countDown();
                log.info("剩余count: {}, {}", countDownLatch.getCount(), Thread.currentThread());
            },lockKey+"_"+i).start();
        }
        countDownLatch.await();
        log.info("redissonCountDownLatchTest end!");
    }
    /**
     * Redisson 可过期性信号量信号量测试<br/>
     *
     */
    @SneakyThrows
    @Test
    public void redissonPermitExpirableSemaphoreTest() {
        log.info("redissonPermitExpirableSemaphoreTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_PERMIT_EXPIRABLE_SEMAPHORE_LOCK_KEY";
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore(lockKey);
        Integer defaultPermits = 2;
        boolean isSet = semaphore.trySetPermits(defaultPermits);
        log.info("设置permits: {}, 设置结果: {}", defaultPermits, isSet);
        log.info("剩余permits: {}", semaphore.availablePermits());
        // 申请一个信号量
        String id = semaphore.acquire();
        log.info("semaphore acquire succeed: {}", Thread.currentThread());
        // 释放一个信号量
        semaphore.release(id);
        log.info("semaphore release succeed: {}", Thread.currentThread());
        log.info("剩余permits: {}", semaphore.availablePermits());
        Thread.sleep(30000L);
        log.info("剩余permits: {}", semaphore.availablePermits());
        Thread.sleep(30000L);
        log.info("redissonPermitExpirableSemaphoreTest end!");
    }

    /**
     * Redisson 信号量测试<br/>
     *
     */
    @SneakyThrows
    @Test
    public void redissonSemaphoreTest() {
        log.info("redissonSemaphoreTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_SEMAPHORE_LOCK_KEY";
        RSemaphore semaphore = redissonClient.getSemaphore(lockKey);
        Integer defaultPermits = 2;
        boolean isSet = semaphore.trySetPermits(defaultPermits);
        log.info("设置permits: {}, 设置结果: {}", defaultPermits, isSet);
        log.info("剩余permits: {}", semaphore.availablePermits());
        // 释放一个信号量
        semaphore.release();
        log.info("semaphore release succeed: {}", Thread.currentThread());
        log.info("剩余permits: {}", semaphore.availablePermits());
        // 申请一个信号量
        semaphore.acquire();
        log.info("semaphore acquire succeed: {}", Thread.currentThread());
        Thread.sleep(30000L);
        log.info("剩余permits: {}", semaphore.availablePermits());
        Thread.sleep(30000L);
        log.info("redissonSemaphoreTest end!");
    }

    /**
     * Redisson 读写锁测试<br/>
     * 基于Redis的Redisson分布式可重入读写锁RReadWriteLock Java对象实现了java.util.concurrent.locks.ReadWriteLock接口。其中读锁和写锁都继承了RLock接口。
     */
    @SneakyThrows
    @Test
    public void redissonReadWriteLockTest() {
        log.info("redissonReadWriteLockTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_READ_WRITE_LOCK_KEY";
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockKey);
        RLock writeLock = readWriteLock.writeLock();
        RLock readLock = readWriteLock.readLock();
        RLock readLock2 = readWriteLock.readLock();
        log.info("{}: try get [readWriteLock] lock: {}", Thread.currentThread(), readWriteLock);
        if (writeLock.tryLock()) {
            try {
                log.info("get [write] lock succeed: {}", Thread.currentThread());
                log.info("try get [read] lock :{}", Thread.currentThread());
                if (readLock.tryLock()) {
                    log.info("get [read] lock succeed:{}", Thread.currentThread());
                    log.info("try get [read] lock2 :{}", Thread.currentThread());
                    if (readLock2.tryLock()) {
                        log.info("get [read] lock2 succeed:{}", Thread.currentThread());
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        Thread.sleep(30000L);
        writeLock.unlock();
        readLock.unlock();
        readLock2.unlock();
        log.info("unlocked: {}", Thread.currentThread());
        log.info("redissonReadWriteLockTest end!");
    }

    /**
     * Redisson 红锁测试<br/>
     * 基于Redis的Redisson红锁RedissonRedLock对象实现了<a href="http://redis.cn/topics/distlock.html">Redlock</a>介绍的加锁算法。该对象也可以用来将多个RLock对象关联为一个红锁，每个RLock对象实例可以来自于不同的Redisson实例。</br>
     */
    @SneakyThrows
    @Test
    public void redissonRedLockTest() {
        log.info("redissonRedLockTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_RED_LOCK_1_KEY";
        String lockKey2 = "SPRING_BOOT_REDISSON_SAMPLE_TEST_RED_LOCK_2_KEY";
        RLock lock = redissonClient.getLock(lockKey);
        RLock lock1 = redissonClient.getLock(lockKey);
        lock1.lock();
        RLock lock2 = redissonClient.getLock(lockKey2);
        RLock redLock = redissonClient.getRedLock(lock, lock2);
        log.info("{}: try get [redLock] lock: {}", Thread.currentThread(), lock);
        if (redLock.tryLock()) {
            try {
                log.info("get lock succeed: {}", Thread.currentThread());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        Thread.sleep(30000L);
        lock1.unlock();
        redLock.unlock();
        log.info("unlocked: {}", Thread.currentThread());
        log.info("redissonRedLockTest end!");
    }

    /**
     * Redisson 联锁测试<br/>
     * 基于Redis的Redisson分布式联锁RedissonMultiLock对象可以将多个RLock对象关联为一个联锁，每个RLock对象实例可以来自于不同的Redisson实例。</br>
     */
    @SneakyThrows
    @Test
    public void redissonMultiLockTest() {
        log.info("redissonFairLockTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_MULTI_LOCK_1_KEY";
        String lockKey2 = "SPRING_BOOT_REDISSON_SAMPLE_TEST_MULTI_LOCK_2_KEY";
        RLock lock = redissonClient.getLock(lockKey);
        RLock lock2 = redissonClient.getLock(lockKey2);
        RLock multiLock = redissonClient.getMultiLock(lock, lock2);
        log.info("{}: try get [multiLock] lock: {}", Thread.currentThread(), lock);
        if (multiLock.tryLock()) {
            try {
                log.info("get lock succeed: {}", Thread.currentThread());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        Thread.sleep(30000L);
        multiLock.unlock();
        log.info("{}: unlocked", Thread.currentThread());
        log.info("redissonFairLockTest end!");
    }

    /**
     * Redisson 公平锁测试
     * (公平锁基于可重入锁)
     */
    @SneakyThrows
    @Test
    public void redissonFairLockTest() {
        log.info("redissonFairLockTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_FAIR_LOCK_KEY";
        RLock lock = redissonClient.getFairLock(lockKey);
        log.info("{}: try get lock: {}", Thread.currentThread(), lock);
        if (lock.tryLock()) {
            try {
                log.info("get lock succeed: {}", Thread.currentThread());
                // 其他线程竞争锁
                otherThreadGetFireLock(lockKey);
                // 获取可重入锁
                getReentrantLock(lockKey);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        Thread.sleep(30000L);
        lock.unlock();
        log.info("{}: unlocked", Thread.currentThread());
        log.info("redissonFairLockTest end!");
    }
    /**
     * Redisson可重入锁测试
     */
    @SneakyThrows
    @Test
    public void redissonReentrantLockTest() {
        log.info("redissonReentrantLockTest start!");
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_NORMAL_LOCK_KEY";
        RLock lock = redissonClient.getLock(lockKey);
        log.info("{}: try get lock: {}", Thread.currentThread(), lock);
        if (lock.tryLock()) {
            try {
                log.info("get lock succeed: {}", Thread.currentThread());
                // 其他线程竞争锁
                otherThreadGetLock(lockKey);
                // 获取可重入锁
                getReentrantLock(lockKey);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        Thread.sleep(30000L);
        lock.unlock();
        log.info("{}: unlocked", Thread.currentThread());
        log.info("redissonReentrantLockTest end!");
    }

    /**
     * 获取可重入锁
     * @param lockKey lockKey
     */
    @SneakyThrows
    public void getReentrantLock(String lockKey) {
        log.info("{}: getReentrantLock: try get lock: ",Thread.currentThread());
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.tryLock()) {
            log.info("get lock succeed: {}", Thread.currentThread());
            Thread.sleep(30000L);
            lock.unlock();
            log.info("{}: unlocked", Thread.currentThread());
        }else{
            log.info("get lock failed: {}", Thread.currentThread());
        }
    }

    /**
     * 获取可重入锁
     * @param lockKey
     */
    public void getReentrantFairLock(String lockKey) throws InterruptedException {
        log.info("{}: getReentrantLock: try get lock: ",Thread.currentThread());
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.tryLock()) {
            log.info("get lock succeed: {}", Thread.currentThread());
            Thread.sleep(30000L);
            lock.unlock();
            log.info("{}: unlocked", Thread.currentThread());
        }else{
            log.info("get lock failed: {}", Thread.currentThread());
        }
    }

    /**
     * 其他线程竞争公平锁
     * @param lockKey lockKey
     */
    public void otherThreadGetFireLock(String lockKey) {
        ThreadGroup tg = new ThreadGroup("SPRING_BOOT_REDISSON_SAMPLE_TEST_FAIR_OTHER_THREAD_GROUP");
        Thread thread = new Thread(tg,()->{
            log.info("{}: otherThreadGetFairLock: try get lock: ",Thread.currentThread());
            RLock lock = otherRedissonClient.getFairLock(lockKey);
            if (lock.tryLock()) {
                log.info("get [fair] lock succeed: {}", Thread.currentThread());
                try {
                    Thread.sleep(30000L);
                } catch (InterruptedException e) {
                }
            }else{
                log.info("get [fair] lock failed, waiting for lock: {}", Thread.currentThread());
                lock.lock();
                log.info("{}: reget [fair] lock succeed: {}", Thread.currentThread());
            }
            log.info("get  [fair] lock end! ",Thread.currentThread());
            lock.unlock();
            log.info("{}:otherThreadGetFairLock: unlocked! ",Thread.currentThread());
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 其他线程竞争锁
     * @param lockKey lockKey
     */
    public void otherThreadGetLock(String lockKey) {
        ThreadGroup tg = new ThreadGroup("SPRING_BOOT_REDISSON_SAMPLE_TEST_OTHER_THREAD_GROUP");
        Thread thread = new Thread(tg,()->{
            log.info("{}: otherThreadGetLock: try get lock: ",Thread.currentThread());
            RLock lock = otherRedissonClient.getLock(lockKey);
            if (lock.tryLock()) {
                log.info("get lock succeed: {}", Thread.currentThread());
            }else{
                log.info("get lock failed, waiting for lock: {}", Thread.currentThread());
                lock.lock();
                log.info("{}: reget lock succeed: {}", Thread.currentThread());
            }
            log.info("get lock end! ",Thread.currentThread());
            lock.unlock();
            log.info("{}:otherThreadGetLock: unlocked! ",Thread.currentThread());
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * redisson rlock 测试
     */
    @Test
    public void redissonLockTest() {
        String lockKey = "SPRING_BOOT_REDISSON_SAMPLE_TEST_LOCK_KEY";
        RLock lock = redissonClient.getLock(lockKey);

    }
}
