package com.java.module.action.sys;

import com.java.common.CommonResult;
import com.java.module.dto.UserInfoDTO;
import com.java.module.dto.UserLoginDTO;
import com.java.module.service.sys.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public AuthorizationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @ApiOperation("登录授权")
    @PostMapping("/login")
    public CommonResult<Map<String,Object>> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        Map<String,Object> userMap = new HashMap<>(2);
        UserInfoDTO userInfoDTO = securityService.login(userLoginDTO);
        userMap.put("userInfo", userInfoDTO);
        return CommonResult.success(userMap);
    }

}
