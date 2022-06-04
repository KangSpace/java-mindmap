package org.kangspace.javamindmap.dubbodemo.service.impl.annotation;


import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.kangspace.javamindmap.dubbodemo.service.annotation.AnnotationDemoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 基于注解的Dubbo Service
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/03/03 19:04
 */
@Slf4j
@Service
public class AnnotationDemoServiceImpl implements AnnotationDemoService {
    @Override
    public String sayHello(String name){
        log.warn("sayHello(String name):"+name);
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ",AnnotationDemoServiceImpl sayHello:" + name;
    }

    @Override
    public String sayHello(String name, String id) {
        log.warn("sayHello(String name, String id):"+name+","+id);
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ",AnnotationDemoServiceImpl sayHello(name,id):" + name+" , id:"+id;
    }

    @Override
    public String sayNo(String name, String id) {
        log.warn("sayNo(String name, String id):"+name+","+id);
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ",AnnotationDemoServiceImpl sayNo(name,id):" + name+" , id:"+id;
    }
}
