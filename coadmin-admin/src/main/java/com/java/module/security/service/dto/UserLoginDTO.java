package com.java.module.security.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录结果DTO
 *
 * @author: jcm
 * @date: 2020/06/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    /**
     * 用户信息
     */
    private UserInfoDTO userInfo;

    /**
     * 认证token
     */
    private String token;

}