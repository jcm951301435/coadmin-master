package com.java.module.sys.service;

import com.java.module.sys.model.SysUser;


/**
 * 用户业务接口
 *
 * @author: jcm
 * @date: 2020/05/17
 */
public interface SysUserService {

    /**
     * 根据用户名查找用户
     *
     * @param username .
     * @return .
     */
    SysUser getUserByUserName(String username);

    /**
     * 添加用户
     *
     * @param user .
     * @return .
     */
    SysUser add(SysUser user);

}
