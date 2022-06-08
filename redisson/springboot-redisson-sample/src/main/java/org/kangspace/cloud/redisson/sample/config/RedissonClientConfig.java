package org.kangspace.cloud.redisson.sample.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * RedissonClient 定义
 *
 * @author kango2gler@gmail.com
 * @since 2022/3/15
 */
@Slf4j
@Configuration
@AutoConfigureAfter(RedissonProperties.class)
public class RedissonClientConfig implements InitializingBean {

    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        return newRedissonClient(redissonProperties);
    }
    @Bean
    public RedissonClient otherRedissonClient(RedissonProperties redissonProperties) {
        return newRedissonClient(redissonProperties);
    }

    public RedissonClient newRedissonClient(RedissonProperties redissonProperties) {
        Config config = new Config();
        ServerType serverType = redissonProperties.getServerType();
        String serverList = redissonProperties.getServerList();
        Objects.requireNonNull(serverType, "serverType must be not null!");
        Objects.requireNonNull(serverList, "serverList must be not null!");
        switch (serverType) {
            case SINGLE:
                config.useSingleServer().
                        setAddress(serverList);
                break;
        }
        // 5分钟
        config.setLockWatchdogTimeout(5 * 60 * 1000L);
        RedissonClient client = Redisson.create(config);
        return client;
    }

    @Override
    public void afterPropertiesSet() {
        log.info("RedissonClientConfig Updated: {}", this);
    }

    /**
     * 服务器类型
     */
    public enum ServerType {
        /**
         * 单节点
         */
        SINGLE,
        /**
         * 集群
         */
        CLUSTER,
        /**
         * 云托管
         */
        REPLICATED,
        /**
         * 哨兵
         */
        SENTINEL,
        /**
         * 主从
         */
        MASTERSLAVE,
        ;
    }
}
