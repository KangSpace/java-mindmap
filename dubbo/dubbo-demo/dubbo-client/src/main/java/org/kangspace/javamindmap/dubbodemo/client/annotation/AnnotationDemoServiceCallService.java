package org.kangspace.javamindmap.dubbodemo.client.annotation;

import org.apache.dubbo.config.annotation.Reference;
import org.kangspace.javamindmap.dubbodemo.service.annotation.AnnotationDemoService;
import org.springframework.stereotype.Service;

/**
 * 基于Annotation声明的Service调用
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/3/2 6:33 下午
 */
@Service
public class AnnotationDemoServiceCallService {
    @Reference(check = false , timeout = 3000)
    private AnnotationDemoService annotationDemoService;

    public String sayHello() {
        return annotationDemoService.sayHello("dubbo-client");
    }
}
