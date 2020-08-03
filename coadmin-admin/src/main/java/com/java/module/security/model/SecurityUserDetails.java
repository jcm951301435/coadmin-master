package com.java.module.security.model;


import com.java.module.sys.model.SysUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * security 认证用户实体
 *
 * @author: jcm
 * @date: 2020/05/24
 */
public class SecurityUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final SysUser sysUser;

    private final Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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

    public SysUser getSysUser() {
        return sysUser;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public SecurityUserDetails(SysUser sysUser, Set<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "SecurityUserDetails{" +
                "sysUser=" + sysUser +
                ", permissions=" + permissions +
                '}';
    }
}
