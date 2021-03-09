package org.kangspace.javamindmap.dubbodemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Dubbo Server提供者Application(基于XML)
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/3/3 10:54
 */
@Slf4j
public class DubboServerXmlProviderApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-server-provider.xml"});
        context.start();
        String runningTip = "Dubbo Server is running...";
        log.info(runningTip);
        System.out.println(runningTip);
        System.in.read(); // 按任意键退出
    }
}
