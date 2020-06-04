package com.java.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 配置类
 * 加载 yml 配置文件
 *
 * @author: jcm
 * @date: 2020/05/20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * JWT存储的请求头
     */
    private String tokenHeader;

    /**
     * JWT加解密使用的密钥
     */
    private String secret;

    /**
     * JWT的超期限时间 秒
     */
    private Long expiration;

    /**
     * JWT负载中拿到开头
     */
    private String tokenStartWith;

    /**
     * 验证码缓存头
     */
    private String verifyCodeKey;

    /**
     * 在线人员缓存头
     */
    private String onlineKey;

    /**
     * 令牌前缀 需加空格
     * @return
     */
    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }

}
