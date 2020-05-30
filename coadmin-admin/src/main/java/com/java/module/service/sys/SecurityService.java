package com.java.module.service.sys;

import com.java.module.dto.UserInfoDTO;
import com.java.module.dto.UserLoginDTO;

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
    UserInfoDTO login(UserLoginDTO userLoginDTO);

}
