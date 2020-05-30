package com.java.module.service.sys.impl;

import com.java.module.dto.UserDetailsDTO;
import com.java.module.dto.UserInfoDTO;
import com.java.module.dto.UserLoginDTO;
import com.java.module.service.sys.SecurityService;
import com.java.module.service.sys.SysUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public SecurityServiceImpl(AuthenticationManager authenticationManager, SysUserService sysUserService) {
        this.authenticationManager = authenticationManager;
        this.sysUserService = sysUserService;
    }

    @Override
    public UserInfoDTO login(UserLoginDTO userLoginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsername(), userLoginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
        return sysUserService.userDetailsToUserInfo(userDetailsDTO);
    }

}
