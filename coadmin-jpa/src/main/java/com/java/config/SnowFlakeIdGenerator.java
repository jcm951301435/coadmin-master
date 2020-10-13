package com.java.config;

import com.java.model.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Properties;

/**
 * 自定义id生成
 *
 * @author: jcm
 * @date: 2020/09/16
 */
@Slf4j
@Component
public class SnowFlakeIdGenerator implements IdentifierGenerator, Configurable, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public SnowFlakeIdGenerator() {
        super();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return getSnowFlake().nextId();
    }

    private SnowFlake getSnowFlake() {
        if (applicationContext != null) {
            try {
                SnowFlakeProvider snowFlakeProvider = applicationContext.getBean(SnowFlakeProvider.class);
                SnowFlake snowFlake = snowFlakeProvider.getSnowFlake();
                if (snowFlake != null) {
                    return snowFlake;
                }
            } catch (Exception e) {
                log.error("无法获取 bean of SnowFlakeProvider", e);
            }
        } else {
            log.error("无法获取spring环境");
        }
        log.warn("无法获取配置的SnowFlake，使用默认配置");
        return new SnowFlake(0, 0);
    }

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SnowFlakeIdGenerator.applicationContext = applicationContext;
    }
}
