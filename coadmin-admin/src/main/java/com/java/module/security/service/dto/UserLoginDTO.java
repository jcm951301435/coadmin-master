package com.java.module.security.service.dto;

import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private UserInfoDTO userInfo;

    private List<SysMenu> menuList;

    private String token;

}
