package org.kangspace.cloud.boot.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author kango2gler@gmail.com
 * @date 2024/4/1
 * @since
 */
@Slf4j
@Aspect
@Component
public class MyAspect {
    @Before("execution(* org.kangspace.cloud.boot.sample.aop.MyServiceImpl.*(..))")
    public void beforeAdvice() {
        log.info("Before advice executed");
    }
}
