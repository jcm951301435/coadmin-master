package com.java.config;

import com.java.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * redis 配置
 *
 * @author: jcm
 * @date: 2020/06/22
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            Map<String, Object> map = new HashMap<>(16);
            Class<?> targetClass = target.getClass();
            map.put("class", targetClass.toGenericString());
            map.put("methodName", method.getName());
            map.put("package", targetClass.getPackage());
            StringBuilder stringBuilder = new StringBuilder();
            for (Object paramsTemp : params) {
                stringBuilder.append(paramsTemp);
                stringBuilder.append(";");
            }
            map.put("params", stringBuilder.toString());
            String json = JsonUtils.objectToJson(map);
            return DigestUtils.sha1DigestAsHex(json);
        };
    }
}
