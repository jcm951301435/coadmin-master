package com.java.config;

import com.java.model.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 自定义id生成
 *
 * @author: jcm
 * @date: 2020/09/16
 */
@Slf4j
@Component
public class SnowFlakeIdGenerator implements IdentifierGenerator, InitializingBean {

    @Value("${snow.flake.id.data}")
    private Long dataId;

    @Value("${snow.flake.id.machine}")
    private Long machineId;

    private SnowFlake snowFlake;

    public SnowFlakeIdGenerator() {
        super();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return getSnowFlake().nextId();
    }

    private SnowFlake getSnowFlake() {
        if (snowFlake == null) {
            init();
        }
        if (snowFlake == null) {
            log.error("未能初始化 snowFlake null");
        }
        return snowFlake;
    }

    private void init() {
        log.info("SnowFlake init dataId:{}, machineId:{}", dataId, machineId);
        if (dataId != null && machineId != null) {
            snowFlake = new SnowFlake(dataId, machineId);
        } else {
            log.warn("未配置snow flake 生成所需的属性 snow.flake.id.data, snow.flake.id.machine");
            snowFlake = new SnowFlake(0, 0);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
