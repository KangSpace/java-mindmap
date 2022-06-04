package org.kangspace.javamindmap.dubbodemo.client.xml;

import org.kangspace.javamindmap.dubbodemo.service.xml.XmlDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基于XML声明的Service调用
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/3/2 6:33 下午
 */
@Service
public class XmlDemoServiceCallService{
    @Autowired
    private XmlDemoService xmlDemoService;

    public String sayHello() {
        return xmlDemoService.sayHello("dubbo-client");
    }
}
