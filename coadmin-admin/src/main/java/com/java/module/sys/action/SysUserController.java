package com.java.module.sys.action;

import com.java.model.CommonResult;
import com.java.module.security.service.SecurityService;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author: jcm
 * @date: 2020/05/17
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "系统：用户接口")
public class SysUserController {

    private final SysUserService userService;

    private final SecurityService securityService;

    public SysUserController(SysUserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @ApiOperation("添加")
    @PostMapping("/create")
    public CommonResult<String> create(@Validated @RequestBody SysUser sysUser) {
        String password = securityService.passwordEncode(sysUser.getPassword());
        sysUser.setPassword(password);
        userService.add(sysUser);
        return CommonResult.success("添加成功！");
    }

    @GetMapping("/list")
    public List<SysUser> list() {
        return userService.listAll();
    }

}
