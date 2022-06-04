package org.kangspace.javamindmap.rocketmqdemo.service;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.kangspace.javamindmap.rocketmqdemo.Application;

/**
 * 生产者测试
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/03/12 16:37
 */
public class Producer{
    public final static String DEFAULT_PRODUCER_GROUP = "JAVA_MINDMAP_PRODUCER";
    private DefaultMQProducer producer = null;
    /**
     * 启动生产者
     */
    public boolean start(){
        producer = new DefaultMQProducer(DEFAULT_PRODUCER_GROUP);
        producer.setNamesrvAddr(Application.DEFAULT_NAMESRV_ADDR);
        try {
            producer.start();
            System.out.println("producer is running");
        } catch (MQClientException e) {
            System.out.println("生产者启动失败:"+e.getMessage());
            e.printStackTrace();
            shutdown();
            return false;
        }
        return true;
    }

    public void shutdown(){
        if (producer != null) {
            producer.shutdown();
        }
    }

    /**
     * 同步发送消息
     */
    public boolean send(Message message){
        checkStarted();
        try {
            SendResult result =  producer.send(message);
            boolean flag = SendStatus.SEND_OK ==result.getSendStatus();
//            System.out.println("[SEND] 同步发送消息结果: "+flag+",MessageQueue:"+result.getMessageQueue()+",MessageId:"+result.getMsgId());
            System.out.println("[SEND] 同步发送消息结果: "+result.toString());
            return flag;
        } catch (Exception e) {
            System.out.println("同步发送消息失败:"+e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 异步发送消息
     */
    public void send(Message message, SendCallback sendCallback){
        checkStarted();
        try {
            producer.send(message,sendCallback);
        } catch (Exception e) {
            System.out.println("异步发送消息失败:"+e.getMessage());
            e.printStackTrace();

        }
    }
    /**
     * 单向发送消息,不关心发送结果
     */
    public void sendOneway(Message message){
        checkStarted();
        try {
            producer.sendOneway(message);
        } catch (Exception e) {
            System.out.println("单向发送消息失败:"+e.getMessage());
            e.printStackTrace();
        }
    }

    private void checkStarted(){
        if (producer == null) {
            throw new IllegalStateException("Producer not start!");
        }
    }

}
