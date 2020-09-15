package com.java.common.util;

import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.model.SysUser;
import com.java.util.SecurityUtils;

/**
 * @author: jcm
 * @date: 2020/09/11
 */
public class SteadySecurityUtils {

    /**
     * 获取当前登录用户
     * @return .
     */
    public static SysUser getCurrentUser() {
        SecurityUserDetails userDetails = getUserDetails();
        return userDetails.getSysUser();
    }

    /**
     * 获取当前登录用户
     * @return .
     */
    public static SecurityUserDetails getUserDetails() {
        return (SecurityUserDetails)SecurityUtils.getUserDetails();
    }

}
