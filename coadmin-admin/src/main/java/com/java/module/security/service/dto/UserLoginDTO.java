package com.java.module.security.service.dto;

/**
 * 登录结果DTO
 *
 * @author: jcm
 * @date: 2020/06/12
 */
public class UserLoginDTO {

    /**
     * 用户信息
     */
    private UserInfoDTO userInfo;

    /**
     * 认证token
     */
    private String token;

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserLoginDTO() {
    }

    public UserLoginDTO(UserInfoDTO userInfo, String token) {
        this.userInfo = userInfo;
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "userInfo=" + userInfo +
                ", token='" + token + '\'' +
                '}';
    }
}
