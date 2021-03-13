package org.kangspace.javamindmap.rocketmqdemo;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.kangspace.javamindmap.rocketmqdemo.service.Consumer;
import org.kangspace.javamindmap.rocketmqdemo.service.Producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 执行测试
 *
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/03/12 16:45
 */
public class Application {
    /**
     * 默认命名服务地址为本地地址
     */
    public final static String DEFAULT_NAMESRV_ADDR = "localhost:9876";
    public final static String TEST_TOPIC = "JAVA_MINDMAP_TOPIC";
    public final static String INPUT_MSG = "INPUT MESSAGE:\n";

    public static void main(String[] args) {
        MessageQueue messageQueue= new MessageQueue();
        messageQueue.setTopic(TEST_TOPIC);

        Consumer consumer = new Consumer();

        //启动拉取式消费者
//        consumer.startPull(messageQueue);
        //启动推送式消费者,
        for (int i = 0; i <= 0; i++) {
            consumer.startPush(i,TEST_TOPIC);
        }

        Producer producer = new Producer();
        //启动生产者
        producer.start();

        cleanHook(() -> {
            producer.shutdown();
            consumer.shutdown();
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
            }
            System.out.println("producer,consumer is shutdown");
        });
        String msg = "";
        while ((msg = readInput(INPUT_MSG)) != null && msg != "") {
            try {
                Message message = new Message(TEST_TOPIC,
                        "TagA",
                        msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(message);
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("[SEND] 异步发送消息成功:"+sendResult.toString());
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("异步发送消息失败:"+throwable);
                        throwable.printStackTrace();
                    }
                });
            } catch (UnsupportedEncodingException e) {
            }
        }
        System.out.println("end!");
    }

    static void cleanHook(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }

    private static String readInput(String prompt) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            System.out.print(prompt);
            str = br.readLine();
        } catch (IOException e) {
        }
        return str;
    }
}
