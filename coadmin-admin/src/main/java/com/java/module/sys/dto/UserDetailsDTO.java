package com.java.module.sys.dto;


import com.java.module.sys.model.SysPermission;
import com.java.module.sys.model.SysUser;
import com.java.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * security 认证用户实体
 *
 * @author: jcm
 * @date: 2020/05/24
 */
@Getter
@AllArgsConstructor
@ToString
public class UserDetailsDTO implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final SysUser sysUser;

    private final List<SysPermission> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> permissionNameSet = permissionList
                .stream().filter(permission -> StringUtils.isNotEmpty(permission.getName()))
                .map(SysPermission::getName).collect(Collectors.toSet());
        return permissionNameSet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        Boolean enable = sysUser.getEnabled();
        return enable != null && enable;
    }

}
