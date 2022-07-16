package org.kangspace.cloud.boot.sample.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Listener 测试
 *
 * @author kango2gler@gmail.com
 * @since 2022/6/20
 */
@Slf4j
@Configuration
public class ListenerMain implements ApplicationContextAware, SmartInitializingSingleton {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 定义可异步执行的
     * @return applicationEventMulticaster
     */
    @Bean
    public SimpleApplicationEventMulticaster applicationEventMulticaster() {
        Executor fixedExecutor = Executors.newFixedThreadPool(2);
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(fixedExecutor);
        return multicaster;
    }




    /**
     * 单例Bean加载完成后执行
     */
    @Override
    public void afterSingletonsInstantiated() {
        this.publishEvent();
    }


    //1. 定义一个Event: DiyEvent

    /**
     * DiyEvent
     */
    public static class DiyEvent extends ApplicationEvent {
        private String name;

        /**
         * Create a new {@code ApplicationEvent}.
         *
         * @param name   name
         */
        public DiyEvent(String name) {
            super(name);
            this.name = name;
        }

        @Override
        public String toString() {
            return "DiyEvent{" +
                    "name='" + name + '\'' +
                    ", source=" + source +
                    "} " + super.toString();
        }
    }

    // 2. 定义事件监听
    // 2.1 通过@EventListener 监听事件, 方法参数为需要监听的事件类型
    // 2.2 通过实现ApplicationListener&lt;DiyEvent&gt;#onApplicationEvent方法

    /**
     * 2.1 通过@EventListener 监听事件, 方法参数为需要监听的事件类型
     */
    @Order(1)
    @EventListener
    public void logEventByEventListener(DiyEvent diyEvent) {
        log.info("[onEvent] logEventByEventListener: thread:{}, diyEvent:{}, order:{}", Thread.currentThread().getName(), diyEvent,1);
    }

    /**
     * 2.2 通过实现ApplicationListener&lt;DiyEvent&gt;#onApplicationEvent方法
     */
    @Component
    public static class DiyEventListener implements ApplicationListener<DiyEvent>, Ordered {
        @Override
        public void onApplicationEvent(DiyEvent event) {
            log.info("[onEvent] DiyEventListener.onApplicationEvent: thread:{}, diyEvent:{}, order: {}", Thread.currentThread().getName(), event, getOrder());
        }

        @Override
        public int getOrder() {
            return 2;
        }
    }

    // 3. 发布事件
    // 3.1 使用ApplicationEventPublisherAware 获取ApplicationEventPublisher 来发布事件
    // 3.2 使用ApplicationContext.publishEvent

    /**
     * 3.1 使用ApplicationEventPublisherAware 获取ApplicationEventPublisher 来发布事件
     */
    @Component
    public static class DivEventPublisher implements ApplicationEventPublisherAware {
        private ApplicationEventPublisher applicationEventPublisher;

        @Override
        public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
            this.applicationEventPublisher = applicationEventPublisher;
        }

        public void publishEvent() {
            applicationEventPublisher.publishEvent(new DiyEvent("event publish by [DivEventPublisher.publishEvent]"));
        }
    }


    public void publishEvent() {
        log.info("run publishEvent start, thread:{}", Thread.currentThread().getName());
        applicationContext.publishEvent(new DiyEvent("event publish by [applicationContext.publishEvent]"));
        applicationContext.getBean(DivEventPublisher.class).publishEvent();
        log.info("run publishEvent end, thread:{}", Thread.currentThread().getName());
    }
}
