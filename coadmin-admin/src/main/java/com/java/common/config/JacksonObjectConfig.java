package com.java.common.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Configuration
public class JacksonObjectConfig {

    /**
     * jackson long 精度丢失
     * 此方法将所有long 转换为 string 输出，可能会导致前端类型错误
     *
     * @return .
     */
    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer customizer = jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
        return customizer;
    }

}
