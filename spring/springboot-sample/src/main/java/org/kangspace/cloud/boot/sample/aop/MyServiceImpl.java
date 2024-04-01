package org.kangspace.cloud.boot.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author kango2gler@gmail.com
 * @date 2024/4/1
 * @since
 */
@Slf4j
@Service("myService")
public class MyServiceImpl implements MyService{
    public void run(){
        log.info("MyServiceImpl run: {}", this);
        this.go();
    }

    @Override
    public void go() {
        log.info("MyServiceImpl go:{}", this);
    }
}
