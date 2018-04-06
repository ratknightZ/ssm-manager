package com.ssm.manager.redisDao;

public interface RedisBaseDao<K,V> {

    V get(K key);

    boolean set(K key, V value, long tiem);
}
