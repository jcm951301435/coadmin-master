package com.java.module.security.service.impl;

import com.java.common.util.CollectionUtils;
import com.java.common.util.StringUtils;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysMenuService;
import com.java.module.sys.service.SysRoleService;
import com.java.module.sys.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Security 认证用户业务处理
 *
 * @author: jcm
 * @date: 2020/05/24
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    private final SysRoleService roleService;

    private final SysMenuService menuService;

    public UserDetailServiceImpl(SysUserService userService, SysRoleService roleService, SysMenuService menuService) {
        this.userService = userService;
        this.roleService = roleService;
        this.menuService = menuService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByUserName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        List<SysRole> roleList = roleService.listByUserId(sysUser.getId());
        Set<String> permissions = new HashSet<>();
        if (CollectionUtils.isNotEmpty(roleList)) {
            permissions = roleList.stream()
                    .filter(sysRole -> StringUtils.isNotBlank(sysRole.getPermission()))
                    .map(SysRole::getPermission)
                    .collect(Collectors.toSet());
            Long[] roles = roleList.stream().map(SysRole::getId).toArray(Long[]::new);
            List<SysMenu> menuList = menuService.listByRoleIds(roles);
            Set<String> menuPermissions = menuList.stream()
                    .filter(sysMenu -> StringUtils.isNotBlank(sysMenu.getPermission()))
                    .map(SysMenu::getPermission)
                    .collect(Collectors.toSet());
            permissions.addAll(menuPermissions);
        }
        return new SecurityUserDetails(sysUser, permissions);
    }

}
