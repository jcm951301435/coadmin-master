package com.java.module.sys.service;

import com.java.module.sys.dto.UserLoginDTO;
import org.springframework.security.core.Authentication;

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
     * @param userLoginDTO
     * @return
     */
    Authentication login(UserLoginDTO userLoginDTO);

    /**
     * 密码加密
     * @param password
     * @return
     */
    String passwordEncode(String password);

}
