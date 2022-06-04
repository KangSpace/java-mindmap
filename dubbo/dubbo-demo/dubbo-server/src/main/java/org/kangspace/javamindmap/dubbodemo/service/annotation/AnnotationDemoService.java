package org.kangspace.javamindmap.dubbodemo.service.annotation;

/**
 * 基于注解的Dubbo Service
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/03/03 19:04
 */
public interface AnnotationDemoService {
    String sayHello(String name);

    String sayHello(String name,String id);

    String sayNo(String name,String id);
}
