package com.java.module.security.action;

import com.java.common.model.CommonResult;
import com.java.module.security.service.SecurityService;
import com.java.module.security.service.dto.LoginParamsDTO;
import com.java.module.security.service.dto.RefreshTokenDTO;
import com.java.module.security.service.dto.UserInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    public AuthorizationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @ApiOperation("登录授权")
    @PostMapping("/login")
    public CommonResult<UserInfoDTO> login(@Validated @RequestBody LoginParamsDTO loginParamsDTO) {
        UserInfoDTO userInfoDTO = securityService.login(loginParamsDTO);
        return CommonResult.success(userInfoDTO);
    }

    @ApiOperation("获取权限")
    @GetMapping("/info")
    public CommonResult<UserInfoDTO> getUserInfo() {
        UserInfoDTO userInfoDTO = securityService.getUserInfo();
        return CommonResult.success(userInfoDTO);
    }

    @ApiOperation("退出登录")
    @DeleteMapping("/logout")
    public CommonResult<String> logout() {
        return CommonResult.success("");
    }

}
