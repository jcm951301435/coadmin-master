package com.java.module.security.service.dto;

import com.java.module.sys.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 用户信息
 *
 * @author: jcm
 * @date: 2020/06/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    /**
     * 用户信息
     */
    private UserDTO user;

    /**
     * 权限列表
     */
    private Set<String> permissions;

}
