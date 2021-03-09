package org.kangspace.javamindmap.dubbodemo.client;

import lombok.extern.slf4j.Slf4j;
import org.kangspace.javamindmap.dubbodemo.client.xml.XmlDemoServiceCallService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Dubbo Client消费者Application(基于XML)
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/3/3 10:54
 */
@Slf4j
public class DubboClientXmlConsumerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-client-consumer.xml"});
        context.start();
        XmlDemoServiceCallService xmlDemoServiceCallService = context.getBean(XmlDemoServiceCallService.class);
        String hello = xmlDemoServiceCallService.sayHello();
        String runningTip = "Dubbo Client is running...";
        log.info(runningTip);
        System.out.println(runningTip);
        log.info("XmlDemoServiceCallService run : "+hello);
    }
}
