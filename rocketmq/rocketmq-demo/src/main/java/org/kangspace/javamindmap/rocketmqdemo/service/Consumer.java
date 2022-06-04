package org.kangspace.javamindmap.rocketmqdemo.service;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.kangspace.javamindmap.rocketmqdemo.Application;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消费者测试
 *
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/03/12 16:38
 */
public class Consumer {
    public final static String DEFAULT_CONSUMER_GROUP = "JAVA_MINDMAP_CONSUMER";
    public final static String DEFAULT_CONSUMER_CLUSTERING_GROUP = "JAVA_MINDMAP_CLUSTER_CONSUMER";
    public final static int DEFAULT_CONSUMER_MAX_NUMBER = 1;
    /**
     * 默认TAG过滤
     */
    public final static String DEFAULT_TAGS = "*";
    public final static long DEFAULT_OFFSET = 1;
    private AtomicBoolean PULL_SHUWDOWN_FLAG = new AtomicBoolean(false);
    private AtomicBoolean PUSH_SHUWDOWN_FLAG = new AtomicBoolean(false);

    /**
     * 启动拉取式消费者
     */
    public void startPull(MessageQueue mq) {
        if (PULL_SHUWDOWN_FLAG.get()) {
            throw new IllegalStateException("startPull has running");
        }
        DefaultMQPullConsumer pullConsumer = new DefaultMQPullConsumer(DEFAULT_CONSUMER_GROUP);
        pullConsumer.setNamesrvAddr(Application.DEFAULT_NAMESRV_ADDR);
        //设置广播式消费,默认为集群式消费
        pullConsumer.setMessageModel(MessageModel.BROADCASTING);
        try {
            pullConsumer.start();
            System.out.println("statPull is running");
        } catch (MQClientException e) {
            throw new IllegalStateException(e);
        }
        int errorCnt = 0;
        while (!PULL_SHUWDOWN_FLAG.get() && errorCnt++<5) {
            try {
                PullResult pr = pullConsumer.pull(mq, DEFAULT_TAGS, DEFAULT_OFFSET, DEFAULT_CONSUMER_MAX_NUMBER);
                System.out.printf("[RECEIVE] %s 拉取式消费 Receive New Messages: %s %n\n", Thread.currentThread().getName(), pr.getMsgFoundList());
            } catch (Exception e) {
                System.out.println("拉取式消费pull消息失败:"+e.getMessage());
                e.printStackTrace();

            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
            }
        }
        if (errorCnt > 5) {
            System.out.println("拉取式消费pull消息错误,消费结束:");
        }
        pullConsumer.shutdown();
    }

    /**
     * 启动推送式消费者
     */
    public void startPush(int consumerId,String topic) {
        if (PUSH_SHUWDOWN_FLAG.get()) {
            throw new IllegalStateException("startPush has running");
        }
        //广播模式消费者组
//        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(DEFAULT_CONSUMER_GROUP);
        //集群模式消费者组
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(DEFAULT_CONSUMER_CLUSTERING_GROUP);
        pushConsumer.setNamesrvAddr(Application.DEFAULT_NAMESRV_ADDR);
        //设置广播式消费,默认为集群式消费;集群消费模式下,一个消费者组内所有消费者分担消息的消费,此时,最大消费者数<=ConsumerQueueNums(也叫MessageQueueNums),多余的消费者不消费数据;小于时,分摊到已有的消费者上;
        // 广播模式下,一个消费者组内每个消费者都消费全部数据
//        pushConsumer.setMessageModel(MessageModel.BROADCASTING);
        pushConsumer.setMessageModel(MessageModel.CLUSTERING);
        pushConsumer.setInstanceName("Consumer-"+consumerId);
        try {
            pushConsumer.subscribe(topic, DEFAULT_TAGS);
            pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    System.out.printf("[RECEIVE] Consumer:%d, %s 推送式消费 Receive New Messages: %s %n",consumerId, Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            pushConsumer.start();
            System.out.println("startPush:"+consumerId+" is running");
        } catch (Exception e) {
            System.out.println("推送式消费消息消费者启动失败:"+e.getMessage());
            e.printStackTrace();
        }
        Thread shutDownThread = new Thread(() -> {
            if (PUSH_SHUWDOWN_FLAG.get()) {
                shutdown();
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                }
            }
        }, "startPushShutDownThread"+consumerId);
        shutDownThread.setDaemon(true);
        shutDownThread.start();
    }

    public void shutdown() {
        PULL_SHUWDOWN_FLAG.compareAndSet(false, true);
        PUSH_SHUWDOWN_FLAG.compareAndSet(false, true);
    }
}
