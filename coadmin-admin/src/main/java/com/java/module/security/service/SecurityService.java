package com.java.module.security.service;

import com.java.module.security.service.dto.LoginParamsDTO;
import com.java.module.security.service.dto.UserInfoDTO;
import com.java.module.security.service.dto.UserLoginDTO;

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
     * @param loginParamsDTO .
     * @return .
     */
    UserLoginDTO login(LoginParamsDTO loginParamsDTO);

    /**
     * 密码加密
     *
     * @param password .
     * @return .
     */
    String passwordEncode(String password);

    /**
     * 获取当前登录人信息
     *
     * @return .
     */
    UserInfoDTO getUserInfo();

}
