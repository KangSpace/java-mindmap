package org.kangspace.javamindmap.dubbodemo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Dubbo Server提供者Application(基于Annotation)
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/3/3 10:54
 */

@Slf4j
public class DubboServerAnnotationProviderApplication {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        String runningTip = "Dubbo Server is running...";
        log.info(runningTip);
        System.out.println(runningTip);
        System.in.read(); // 按任意键退出
    }

    /**
     * 配置类
     */
    @Configuration
    @EnableDubbo(scanBasePackages = "org.kangspace.javamindmap.dubbodemo.service.impl.annotation")
    @EnableDubboConfig
    @PropertySource("classpath:/dubbo-server-provider.properties")
    public static class ProviderConfiguration{
    }
}
