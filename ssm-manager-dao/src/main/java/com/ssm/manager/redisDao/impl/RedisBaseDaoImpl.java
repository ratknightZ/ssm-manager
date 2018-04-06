package com.ssm.manager.redisDao.impl;

import com.ssm.manager.redisDao.RedisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisBaseDaoImpl<K,V> implements RedisBaseDao<K,V> {

    @Autowired
    private RedisTemplate<K,V> redisTemplate;

    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(K key, V value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
