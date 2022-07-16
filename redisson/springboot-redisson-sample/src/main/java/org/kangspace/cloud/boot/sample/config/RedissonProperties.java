package org.kangspace.cloud.boot.sample.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * RedissonProperties
 * @author kango2gler@gmail.com
 * @since 2022/3/21
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties implements InitializingBean {
    /**
     * 服务器类型
     */
//    @Value("${serverType}")
    private RedissonClientConfig.ServerType serverType;

    /**
     * 服务器列表
     */
//    @Value("${serverList}")
    private String serverList;

    @Override
    @Lookup
    public void afterPropertiesSet() {
        log.info("RedissonProperties 加载完成: {}", this);
    }

    public RedissonClientConfig.ServerType getServerType() {
        return serverType;
    }

    public void setServerType(RedissonClientConfig.ServerType serverType) {
        this.serverType = serverType;
    }

    public String getServerList() {
        return serverList;
    }

    public void setServerList(String serverList) {
        this.serverList = serverList;
    }
}
