package org.kangspace.cloud.redisson.sample.service;

/**
 * RedisService
 *
 * @author kango2gler@gmail.com
 * @since 2022/3/15
 */
public interface RedisService {


    /**
     * 加锁
     *
     * @param key key
     * @return boolean
     */
    boolean lock(String key);

    /**
     * 获取锁
     *
     * @param key key
     * @param ttl 超时时间,ms
     * @return boolean
     */
    boolean lock(String key, long ttl);

    /**
     * 获取锁
     *
     * @param key
     * @param ttl
     * @param waiting
     * @return
     */
    boolean lock(String key, long ttl, boolean waiting);

}
