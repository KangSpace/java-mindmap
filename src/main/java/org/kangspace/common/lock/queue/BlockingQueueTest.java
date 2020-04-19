package org.kangspace.common.lock.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 2020/1/6 18:05
 *
 * @author kango2gler@gmail.com
 */
public class BlockingQueueTest {
    static void main(){
        //底层为对象数组,必须提供队列大小
        //入队出队使用同一锁,2个条件锁,同一时间只能有1个线程操作入队或出队
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2,false);
        //底层为单向链表,可提供队列大小，不指定时为Integer.MAX_VALUE
        //入队出队使用2个不同的锁,2个条件锁,同一时间可以有2个线程操作入队或出队，但队列数量更新为AtomicInteger更新
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(2);
        //SynchronousQueue没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续添加新的元素。
        SynchronousQueue synchronousQueue = new SynchronousQueue();
    }
    public static void main(String[] args) {
        main();
    }
}
