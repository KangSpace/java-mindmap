package org.kangspace.cloud.boot.sample.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件加载:
 *
 * 1. spring.profiles.active,spring.profiles.include扫描顺序:
 * 支持4种类型配置文件: property(.properties,.xml), yaml(.yml,.yaml)
 * 支持5个文件位置: "file:./config/,file:./config/*\/,file:./,classpath:/config/,classpath:/" {@link org.springframework.boot.context.config.ConfigFileApplicationListener#DEFAULT_SEARCH_LOCATIONS}的反序
 * a. bootstrap.yml(active,include)->bootstrap[-active].yml
 * b. application.yml(active,include)->application[-active].yml
 *
 * 2. 配置文件覆盖顺序
 *
 * application[-active].yml 覆盖 application.yml, 若 application[-active].yml中active了其他profile, 则其他profile中的配置则生效,
 * 覆盖bootstrap[-active].yml 覆盖 bootstrap.yml
 *
 * @author kango2gler@gmail.com
 * @since 2022/4/10
 */
@Data
@Slf4j
@Component
public class ConfigLoadFromYml implements InitializingBean {

    @Value("${configLoad.value:}")
    private String value;

    @Override
    public void afterPropertiesSet() {
        log.info("ConfigLoadFromYml 加载完成: {}", this);
    }
}
