package com.ssm.manager.redisDao.impl;

import com.ssm.manager.redisDao.RedisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisBaseDaoImpl implements RedisBaseDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value, long time) {
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
