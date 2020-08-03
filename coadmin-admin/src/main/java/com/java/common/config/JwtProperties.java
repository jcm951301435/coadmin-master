package com.java.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 配置类
 * 加载 yml 配置文件
 *
 * @author: jcm
 * @date: 2020/05/20
 */
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
     * @return .
     */
    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public void setTokenStartWith(String tokenStartWith) {
        this.tokenStartWith = tokenStartWith;
    }

    public String getVerifyCodeKey() {
        return verifyCodeKey;
    }

    public void setVerifyCodeKey(String verifyCodeKey) {
        this.verifyCodeKey = verifyCodeKey;
    }

    public String getOnlineKey() {
        return onlineKey;
    }

    public void setOnlineKey(String onlineKey) {
        this.onlineKey = onlineKey;
    }
}
