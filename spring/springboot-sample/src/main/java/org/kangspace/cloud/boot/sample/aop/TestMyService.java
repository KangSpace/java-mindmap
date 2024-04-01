package org.kangspace.cloud.boot.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kango2gler@gmail.com
 * @date 2024/4/1
 * @since
 */
@Slf4j
@Service
public class TestMyService implements InitializingBean {
    @Resource
    private MyService myService;
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("myService:{}",myService);
        myService.run();
    }
}
