package com.java.module.sys.action;

import com.java.common.util.SteadySecurityUtils;
import com.java.model.CommonResult;
import com.java.module.sys.action.vo.MenuPermissionsVO;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.SysMenuService;
import com.java.module.sys.dto.query.MenuListQueryDTO;
import com.java.module.sys.service.dto.MenuTreeDTO;
import com.java.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author: jcm
 * @date: 2020/08/05
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "系统：菜单接口")
public class SysMenuController {

    private final SysMenuService menuService;

    public SysMenuController(SysMenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation("当前用户菜单列表")
    @GetMapping("/menuPermissions")
    public CommonResult<List<MenuPermissionsVO>> menuPermissions() {
        List<MenuPermissionsVO> menuPermissionsVOList = menuService.buildMenuVO(SteadySecurityUtils.getUserDetails());
        return CommonResult.success(menuPermissionsVOList);
    }

    @ApiOperation("菜单列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:menu:list')")
    public CommonResult<List<MenuTreeDTO>> list(MenuListQueryDTO params) {
        List<MenuTreeDTO> sysMenuList = menuService.treeList(params);
        return CommonResult.success(sysMenuList);
    }

    @ApiOperation("全部菜单列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<MenuTreeDTO>> listAll(MenuListQueryDTO params) {
        List<MenuTreeDTO> sysMenuList = menuService.treeList(params);
        return CommonResult.success(sysMenuList);
    }

    @ApiOperation("导出菜单")
    @GetMapping(value = "/export")
    @PreAuthorize("hasAnyAuthority('admin', 'sys:menu:list')")
    public void export(MenuListQueryDTO params, HttpServletResponse response) {
        List<MenuTreeDTO> sysMenuList = menuService.treeListSort(params);
        ExcelUtils.downLoad(sysMenuList, MenuTreeDTO.class, response);
    }

    @ApiOperation("添加菜单")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:menu:add')")
    public CommonResult<String> create(@Validated(SysMenu.Create.class) @RequestBody SysMenu menu) {
        int result = menuService.create(menu);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failure("添加失败");
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:menu:update')")
    public CommonResult<String> update(@Validated(SysMenu.Update.class) @RequestBody SysMenu menu) {
        int result = menuService.update(menu);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failure("修改失败");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:menu:delete')")
    public CommonResult<String> delete(@RequestBody List<Long> ids) {
        menuService.delete(ids);
        return CommonResult.success("删除成功");
    }

}
