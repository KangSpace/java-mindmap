package org.kangspace.javamindmap.dubbodemo.service.impl.xml;

import org.kangspace.javamindmap.dubbodemo.service.xml.XmlDemoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 基于XML声明的Service demo实现
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021-03-02 18:29:38
 */
public class XmlDemoServiceImpl implements XmlDemoService {
    @Override
    public String sayHello(String name){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ",XmlDemoServiceImpl sayHello:" + name;
    }
}
