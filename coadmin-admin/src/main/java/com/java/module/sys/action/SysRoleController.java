package com.java.module.sys.action;

import com.java.model.CommonPage;
import com.java.model.CommonResult;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.service.SysRoleService;
import com.java.module.sys.service.dto.RoleQueryParamsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色接口
 *
 * @author: jcm
 * @date: 2020/09/15
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "系统：角色接口")
public class SysRoleController {

    private final SysRoleService roleService;

    public SysRoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("角色列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:role:list')")
    public CommonResult<CommonPage<SysRole>> list(RoleQueryParamsDTO params,
                                                  @Validated CommonPage<SysRole> commonPage) {
        CommonPage<SysRole> sysRoles = roleService.page(params, commonPage);
        return CommonResult.success(sysRoles);
    }

    @ApiOperation("导出角色")
    @GetMapping(value = "/export")
    @PreAuthorize("hasAnyAuthority('admin', 'sys:role:list')")
    public void export(RoleQueryParamsDTO params, HttpServletResponse response) {
//        List<ListExportVO> sysLists = roleService.listExport(params);
//        ExcelUtils.downLoad(sysLists, ListExportVO.class, response);
    }

    @ApiOperation("添加角色")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:role:add')")
    public CommonResult<String> create(@Validated(SysRole.Create.class) @RequestBody SysRole sysRole) {
        int result = roleService.create(sysRole);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failure("添加失败");
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:role:update')")
    public CommonResult<String> update(@Validated(SysRole.Update.class) @RequestBody SysRole sysRole) {
        int result = roleService.update(sysRole);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failure("修改失败");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:role:delete')")
    public CommonResult<String> delete(@RequestBody List<Long> ids) {
        roleService.delete(ids);
        return CommonResult.success("删除成功");
    }

}
