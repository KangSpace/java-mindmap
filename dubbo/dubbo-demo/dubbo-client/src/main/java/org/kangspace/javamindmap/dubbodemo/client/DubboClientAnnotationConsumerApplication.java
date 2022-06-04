package org.kangspace.javamindmap.dubbodemo.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.kangspace.javamindmap.dubbodemo.client.annotation.AnnotationDemoServiceCallService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * Dubbo Client Application(基于注解)
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/3/3 10:54
 */

@Slf4j
public class DubboClientAnnotationConsumerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        AnnotationDemoServiceCallService annotationDemoServiceCallService = context.getBean(AnnotationDemoServiceCallService.class);
        String hello = annotationDemoServiceCallService.sayHello();
        String runningTip = "Dubbo Client is running...";
        log.info(runningTip);
        System.out.println(runningTip);
        log.info("DubboClientAnnotationProviderApplication run : "+hello);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置类
     */
    @Configuration
    @EnableDubbo(scanBasePackages = "org.kangspace.javamindmap.dubbodemo.client.annotation")
    @EnableDubboConfig
    @PropertySource("classpath:/dubbo-client-consumer.properties")
    @ComponentScan(basePackages = "org.kangspace.javamindmap.dubbodemo.client.annotation")
    public static class ConsumerConfiguration{
    }
}
