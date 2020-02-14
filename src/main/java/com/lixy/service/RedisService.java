package com.lixy.service;

import java.util.concurrent.TimeUnit;

public interface RedisService<K, F, V> {
    /**
     * hget
     * @param key
     * @param field
     * @return
     */
    V hget(K key, F field);

    /**
     * hset
     * @param key
     * @param field
     * @param value
     * @param expireTime
     * @param unit
     */
    void hset(K key, F field, V value, long expireTime, TimeUnit unit);

    /**
     * hset
     * @param key
     * @param field
     * @param value
     */
    void hset(K key, F field, V value);
}
