package com.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author: jcm
 * @date: 2020/09/21
 */
@Configuration
@EnableRedisRepositories(basePackages = "com.java.*.*.redis")
public class RepositoryConfig {
}
