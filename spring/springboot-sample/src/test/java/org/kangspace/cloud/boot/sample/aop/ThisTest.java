package org.kangspace.cloud.boot.sample.aop;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类内this调用测试
 * @author kango2gler@gmail.com
 * @date 2024/4/1
 * @since
 */
@Slf4j
@RunWith(SpringRunner.class)
public class ThisTest {

    @Component
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    public static class Main implements ApplicationContextAware{
        @Getter
        private static ApplicationContext applicationContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }



    public interface MyService{
        void run();
        void go();
    }


    @Service
    public class MyServiceImpl implements MyService{
        public void run(){
            log.info("MyServiceImpl run");
            this.go();
        }

        @Override
        public void go() {
            log.info("MyServiceImpl go");
        }
    }

    @Aspect
    @Component
    public static class MyAspect {
        @Before("execution(* org.kangspace.cloud.boot.sample.aop.ThisTest.MyService.*(..))")
        public void beforeAdvice() {
            System.out.println("Before advice executed");
        }

    }

    @Test
    public void test() {
        log.info("开始执行");
        MyService myService = Main.getApplicationContext().getBean(MyService.class);
        log.info("myService: {}", myService);
        myService.run();
    }
}
