package org.kangspace.cloud.redisson.sample.service.impl;

import org.kangspace.cloud.redisson.sample.service.RedisService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Redisson 实现
 * @author kango2gler@gmail.com
 * @since 2022/3/15
 */
@Service
public class RedissonServiceImp implements RedisService {
    @Resource
    private RedissonClient redissonClient;

    @Override
    public boolean lock(String key) {
        return false;
    }

    @Override
    public boolean lock(String key, long ttl) {
        return false;
    }

    @Override
    public boolean lock(String key, long ttl, boolean waiting) {
        return false;
    }
}
