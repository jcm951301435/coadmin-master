package com.java.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author: jcm
 * @date: 2020/07/01
 */
@Component
public class RedisUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置缓存有效期
     *
     * @param key     .
     * @param timeout .
     * @return .
     */
    public boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存有效期
     *
     * @param key      .
     * @param timeout  .
     * @param timeUnit .
     * @return .
     */
    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        try {
            redisTemplate.expire(key, timeout, timeUnit);
        } catch (Exception e) {
            LOGGER.error("设置缓存有效期失败", e);
            return false;
        }
        return true;
    }

    /**
     * 获取缓存有效时间
     *
     * @param key .
     * @return .
     */
    public long getExpire(String key) {
        Long result = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (result == null) {
            return 0L;
        }
        return (long) result;
    }

    /**
     * 是否存在 key
     *
     * @param key .
     * @return .
     */
    public boolean hashKey(String key) {
        Boolean result = redisTemplate.hasKey(key);
        if (result == null) {
            return false;
        }
        return (boolean) result;
    }

    /**
     * 根据 key 获取值
     *
     * @param key .
     * @return .
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置值
     *
     * @param key   .
     * @param value .
     * @return .
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            LOGGER.error("设置redis值错误", e);
            return false;
        }
        return true;
    }

    /**
     * 设置值 且设置有效时间
     *
     * @param key     .
     * @param value   .
     * @param timeout .
     * @return .
     */
    public boolean set(String key, Object value, long timeout) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("设置redis值错误", e);
            return false;
        }
        return true;
    }

    /**
     * 获取 hash 中的item
     * @param key .
     * @param item .
     * @return .
     */
    public Object hashValueGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hash
     * @param key .
     * @return .
     */
    public Map<Object, Object> hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

}
