package com.java.config;

import com.java.model.SnowFlake;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: jcm
 * @date: 2020/09/22
 */
@Slf4j
@Component
@Data
public class SnowFlakeProvider implements InitializingBean {

    @Value("${snow.flake.id.data}")
    private Long dataId;

    @Value("${snow.flake.id.machine}")
    private Long machineId;

    private SnowFlake snowFlake;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("SnowFlake init dataId:{}, machineId:{}", dataId, machineId);
        if (dataId != null && machineId != null) {
            snowFlake = new SnowFlake(dataId, machineId);
        } else {
            log.warn("未配置snow flake 生成所需的属性 snow.flake.id.data, snow.flake.id.machine");
            snowFlake = new SnowFlake(0, 0);
        }
    }
}
