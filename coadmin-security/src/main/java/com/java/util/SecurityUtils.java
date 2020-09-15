package com.java.util;

import com.java.exception.ProjectRunTimeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * 认证工具类
 *
 * @author: jcm
 * @date: 2020/06/14
 */
public class SecurityUtils {

    private final static String ADMIN_USERNAME = "admin";

    private final static String ADMIN_PERMISSION_NAME = "admin";

    /**
     * 获取当前认证信息
     *
     * @return .
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前认证用户信息
     *
     * @return .
     */
    public static UserDetails getUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            throw new ProjectRunTimeException("当前登录状态过期");
        }
        return (UserDetails) authentication.getPrincipal();
    }

    /**
     * 获取当前认证用户id
     *
     * @return .
     */
    public static Long getCurrentUserId() {
        UserDetails userDetails = getUserDetails();
        Map<String, Object> map = JsonUtils.objectToMap(userDetails);
        Object idObj = map.get("id");
        return (Long) idObj;
    }

    /**
     * 获取当前认证用户username
     *
     * @return .
     */
    public static String getCurrentUserName() {
        UserDetails userDetails = getUserDetails();
        return userDetails.getUsername();
    }

    /**
     * 是否是admin
     *
     * @param username .
     * @return .
     */
    public static boolean isAdmin(String username) {
        return ADMIN_USERNAME.equals(username);
    }

    /**
     * admin 默认权限
     *
     * @return .
     */
    public static String getAdminPermission() {
        return ADMIN_PERMISSION_NAME;
    }

}
