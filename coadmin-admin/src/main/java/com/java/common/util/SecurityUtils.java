package com.java.common.util;

import com.java.module.security.model.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 认证工具类
 *
 * @author: jcm
 * @date: 2020/06/14
 */
public class SecurityUtils {

    /**
     * 获取当前认证信息
     * @return .
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前认证用户信息
     * @return .
     */
    public static SecurityUserDetails getUserDetails() {
        Authentication authentication = getAuthentication();
        return (SecurityUserDetails) authentication.getPrincipal();
    }

}
