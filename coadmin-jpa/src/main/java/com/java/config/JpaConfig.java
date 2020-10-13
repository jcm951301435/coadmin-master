package com.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 开启审计功能 自动注入值
 *
 * @author: jcm
 * @date: 2020/09/16
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(basePackages= "com.java.*.*.dao", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaConfig {
}
