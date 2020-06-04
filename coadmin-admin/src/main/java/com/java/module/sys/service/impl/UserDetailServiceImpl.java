package com.java.module.sys.service.impl;

import com.java.module.sys.dto.UserDetailsDTO;
import com.java.module.sys.model.SysPermission;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Security 认证用户业务处理
 *
 * @author: jcm
 * @date: 2020/05/24
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    public UserDetailServiceImpl(SysUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByUserName(username);
        List<SysPermission> permissionList = new ArrayList<>();
        return new UserDetailsDTO(sysUser, permissionList);
    }

}
