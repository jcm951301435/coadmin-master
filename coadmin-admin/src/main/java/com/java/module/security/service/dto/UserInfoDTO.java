package com.java.module.security.service.dto;

import com.java.module.sys.service.dto.UserDTO;

import java.util.Set;

/**
 * 用户信息
 *
 * @author: jcm
 * @date: 2020/06/14
 */
public class UserInfoDTO {

    /**
     * 用户信息
     */
    private UserDTO user;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public UserInfoDTO() {
    }

    public UserInfoDTO(UserDTO user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "user=" + user +
                ", permissions=" + permissions +
                '}';
    }
}
