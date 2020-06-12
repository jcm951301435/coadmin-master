package com.java.module.security.service.impl;

import com.java.common.config.provider.TokenProvider;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.security.service.SecurityService;
import com.java.module.security.service.dto.UserLoginDTO;
import com.java.module.security.service.dto.LoginParamsDTO;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.dto.UserInfoDTO;
import com.java.module.sys.service.mapper.UserInfoMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认证业务接口实现
 *
 * @author: jcm
 * @date: 2020/05/23
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserInfoMapper userInfoMapper;

    public SecurityServiceImpl(AuthenticationManager authenticationManager,
                               TokenProvider tokenProvider, PasswordEncoder passwordEncoder,
                               UserInfoMapper userInfoMapper) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserLoginDTO login(LoginParamsDTO loginParamsDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginParamsDTO.getUsername(), loginParamsDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        SysUser sysUser = securityUserDetails.getSysUser();
        List<SysMenu> menuList = securityUserDetails.getMenuList();
        UserInfoDTO userInfoDTO = userInfoMapper.toDto(sysUser);
        String token = tokenProvider.createToken(securityUserDetails);
        return new UserLoginDTO(userInfoDTO, menuList, token);
    }

    @Override
    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }

}
