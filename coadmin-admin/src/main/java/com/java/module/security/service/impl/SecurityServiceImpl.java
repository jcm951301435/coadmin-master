package com.java.module.security.service.impl;

import com.java.common.config.provider.TokenProvider;
import com.java.common.util.SecurityUtils;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.security.service.SecurityService;
import com.java.module.security.service.dto.LoginParamsDTO;
import com.java.module.security.service.dto.UserInfoDTO;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.dto.UserDTO;
import com.java.module.sys.service.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    private final UserMapper userMapper;

    public SecurityServiceImpl(AuthenticationManager authenticationManager,
                               TokenProvider tokenProvider, PasswordEncoder passwordEncoder,
                               UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserInfoDTO login(LoginParamsDTO loginParamsDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginParamsDTO.getUsername(), loginParamsDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return getUserInfoFromAuth(authentication);
    }

    @Override
    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public UserInfoDTO getUserInfo() {
        Authentication authentication = SecurityUtils.getAuthentication();
        return getUserInfoFromAuth(authentication);
    }

    /**
     * 从认证信息中获取 UserInfo
     * @param authentication .
     * @return .
     */
    private UserInfoDTO getUserInfoFromAuth(Authentication authentication) {
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        SysUser sysUser = securityUserDetails.getSysUser();
        Set<String> permissions = securityUserDetails.getPermissions();
        UserDTO userDTO = userMapper.toDto(sysUser);
        return new UserInfoDTO(userDTO, permissions);
    }

}
