package com.java.module.action.sys;

import com.java.common.CommonResult;
import com.java.module.model.sys.SysUser;
import com.java.module.service.sys.SysUserService;
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

    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    @ApiOperation("添加")
    @PostMapping("/create")
    public CommonResult<String> create(@Validated @RequestBody SysUser sysUser) {
        userService.add(sysUser);
        return CommonResult.success("添加成功！");
    }

    @GetMapping("/list")
    public List<SysUser> list() {
        return userService.listAll();
    }

}
