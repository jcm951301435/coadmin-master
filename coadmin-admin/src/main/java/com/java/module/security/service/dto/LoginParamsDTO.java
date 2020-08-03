package com.java.module.security.service.dto;

import javax.validation.constraints.NotBlank;

/**
 * 登录dto
 *
 * @author: jcm
 * @date: 2020/05/23
 */
public class LoginParamsDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
