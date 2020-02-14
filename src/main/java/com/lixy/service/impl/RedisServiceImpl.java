package com.lixy.service.impl;

import com.lixy.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RedisServiceImpl<K,F,V> implements RedisService<K,F,V> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public V hget(K key, F field)  {
        HashOperations<K,F,V> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, field);
    }
    @Override
    public void hset(K key, F field, V value, long expireTime, TimeUnit unit) {
        HashOperations<K,F,V> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);
        //设置过期时间
        hashOperations.getOperations().expire(key, expireTime, unit);
    }
    @Override
    public void hset(K key, F field, V value) {
        HashOperations<K,F,V> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);
    }
}
