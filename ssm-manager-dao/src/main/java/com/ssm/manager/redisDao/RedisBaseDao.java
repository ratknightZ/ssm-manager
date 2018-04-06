package com.ssm.manager.redisDao;

public interface RedisBaseDao {

    Object get(String key);

    public boolean set(String key, Object value, long tiem);
}
