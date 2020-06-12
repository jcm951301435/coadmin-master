package com.java.module.security.service;

import com.java.module.security.service.dto.UserLoginDTO;
import com.java.module.security.service.dto.LoginParamsDTO;

/**
 * 认证业务接口
 *
 * @author: jcm
 * @date: 2020/05/23
 */
public interface SecurityService {

    /**
     * 登录
     *
     * @param loginParamsDTO
     * @return
     */
    UserLoginDTO login(LoginParamsDTO loginParamsDTO);

    /**
     * 密码加密
     * @param password
     * @return
     */
    String passwordEncode(String password);



}
