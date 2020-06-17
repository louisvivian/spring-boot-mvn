package com.louis.service.springbootmvn.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * RedisRepository
 *
 * @author wangxing
 */
@Component
public class RedisRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 判断是否存在Key
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取 商品扩展信息
     */
    public String get(String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return value;
    }

    /**
     * 获取 商品扩展信息列表
     */
    public Set<String> getKeys(String keys) {
        Set<String> stringList = redisTemplate.keys(keys);
        if (stringList == null) {
            return null;
        }
        return stringList;
    }

    /**
     * 保存 商品扩展信息
     */
    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 删除 商品扩展信息
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量 删除 Redis Key
     *
     * @param strings
     */
    public void deleteBatch(Set<String> strings) {
        redisTemplate.delete(strings);
    }


    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */

    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


}
