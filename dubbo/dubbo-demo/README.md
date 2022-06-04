# Dubbo Demo
It's a demo project for Dubbo.
1. run local zookeeper
2. run dubbo-server project
3. run dubbo-client 

## Dubbo Demo 有2种实现方式
### 1. 基于Spring XML
参考:
> 1. [快速开始](https://dubbo.apache.org/zh/docs/v2.7/user/quick-start/)  
> 2. [Github Demo](https://github.com/apache/dubbo/blob/master/dubbo-demo/dubbo-demo-xml/)

相关类:
```java
//org.kangspace.javamindmap.rocketmqdemo.Provider
org.kangspace.javamindmap.dubbodemo.service.DubboServerXmlProviderApplication
//接口
org.kangspace.javamindmap.dubbodemo.service.xml.XmlDemoService
//实现
org.kangspace.javamindmap.dubbodemo.service.impl.xml.XmlDemoServiceImpl       
//配置
java-mindmap/dubbo-demo/dubbo-server/src/main/resources/dubbo-server-provider.xml
//Consumer
org.kangspace.javamindmap.dubbodemo.client.DubboClientXmlConsumerApplication
//调用服务
org.kangspace.javamindmap.dubbodemo.client.xml.XmlDemoServiceCallService
//配置
java-mindmap/dubbo-demo/dubbo-client/src/main/resources/dubbo-client-consumer.xml
```
### 2. 基于注解(SpringBoot/Spring AnnotationConfigApplicationContext)

参考:
> 1. [注解配置](https://dubbo.apache.org/zh/docs/v2.7/user/configuration/annotation/)
> 2. [Github Demo](https://github.com/apache/dubbo/blob/master/dubbo-demo/dubbo-demo-annotation)

相关类
```java
//org.kangspace.javamindmap.rocketmqdemo.Provider
org.kangspace.javamindmap.dubbodemo.service.DubboServerAnnotationProviderApplication
//接口
        org.kangspace.javamindmap.dubbodemo.service.annotation.AnnotationDemoService
//实现
        org.kangspace.javamindmap.dubbodemo.service.impl.annotation.AnnotationDemoServiceImpl
//配置
        java-mindmap/dubbo-demo/dubbo-server/src/main/resources/dubbo-server-provider.properties
//Consumer
        org.kangspace.javamindmap.dubbodemo.client.DubboClientAnnotationConsumerApplication
//调用服务
        org.kangspace.javamindmap.dubbodemo.client.annotation.AnnotationDemoServiceCallService
//配置
        java-mindmap/dubbo-demo/dubbo-client/src/main/resources/dubbo-client-consumer.properties

```