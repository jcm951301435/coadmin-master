package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * 启用swagger
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = RedisRepositoriesAutoConfiguration.class)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
