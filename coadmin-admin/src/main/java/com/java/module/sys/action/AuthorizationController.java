package com.java.module.sys.action;

import com.java.common.config.provider.TokenProvider;
import com.java.common.model.CommonResult;
import com.java.module.sys.dto.UserDetailsDTO;
import com.java.module.sys.dto.UserInfoDTO;
import com.java.module.sys.dto.UserLoginDTO;
import com.java.module.sys.service.SecurityService;
import com.java.module.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证处理控制器
 *
 * @author: jcm
 * @date: 2020/05/23
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {

    private final SecurityService securityService;

    private final SysUserService sysUserService;

    private final TokenProvider tokenProvider;

    public AuthorizationController(SecurityService securityService, SysUserService sysUserService,
                                   TokenProvider tokenProvider) {
        this.securityService = securityService;
        this.sysUserService = sysUserService;
        this.tokenProvider = tokenProvider;
    }

    @ApiOperation("登录授权")
    @PostMapping("/login")
    public CommonResult<Map<String,Object>> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        Map<String,Object> userMap = new HashMap<>(2);
        Authentication authentication = securityService.login(userLoginDTO);
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
        UserInfoDTO userInfoDTO = sysUserService.userDetailsToUserInfo(userDetailsDTO);
        String token = tokenProvider.createToken(userDetailsDTO);
        userMap.put("userInfo", userInfoDTO);
        userMap.put("token", token);
        return CommonResult.success(userMap);
    }

}
