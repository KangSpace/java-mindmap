package org.kangspace.cloud.boot.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * @author kango2gler@gmail.com
 * @since 2022/3/16
 */
@Slf4j
@Configuration
public class RestTemplateConfig {
    /**
     * 默认的RestTemplate
     *
     * @return {@link RestTemplate}
     */
    @Bean(value = "restTemplate")
    @Primary
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * RestTemplate2
     *
     * @return {@link RestTemplate}
     */
    @Bean(value = "restTemplate2")
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }
}
