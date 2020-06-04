package com.java.module.sys.service.impl;

import com.java.module.sys.dto.UserLoginDTO;
import com.java.module.sys.service.SecurityService;
import com.java.module.sys.service.SysUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证业务接口实现
 *
 * @author: jcm
 * @date: 2020/05/23
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;

    private final SysUserService sysUserService;

    private final PasswordEncoder passwordEncoder;

    public SecurityServiceImpl(AuthenticationManager authenticationManager, SysUserService sysUserService,
                               PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication login(UserLoginDTO userLoginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsername(), userLoginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }

}
