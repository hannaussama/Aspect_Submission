package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisBridge {

    @Autowired
    private StringRedisTemplate redis;

    public Boolean tryLock(String key, String value, Duration ttl) {
        return redis.opsForValue().setIfAbsent(key, value, ttl);
    }

    public void put(String key, String value, Duration ttl) {
        redis.opsForValue().set(key, value, ttl);
    }

    public String get(String key) {
        return redis.opsForValue().get(key);
    }

    public Boolean delete(String key) {
        return redis.delete(key);
    }

    public Long increment(String key, Duration ttl) {
        Long current = redis.opsForValue().increment(key);
        if (current != null && current == 1) {
            redis.expire(key, ttl);
        }
        return current;
    }
}
