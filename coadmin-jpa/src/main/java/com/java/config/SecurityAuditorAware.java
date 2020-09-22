package com.java.config;

import com.java.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 审计 自定义注入值
 *
 * @author: jcm
 * @date: 2020/09/16
 */
@Component("auditorAware")
public class SecurityAuditorAware implements AuditorAware<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityAuditorAware.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        String currentUserName = "none";
        try {
            currentUserName = SecurityUtils.getCurrentUserName();
        } catch (Exception e) {
            LOGGER.error("jpa 审计获取当前用户失败", e);
        }
        return Optional.of(currentUserName);
    }

}
