package com.java.module.sys.action;

import com.java.model.CommonResult;
import com.java.module.sys.model.SysDepart;
import com.java.module.sys.service.SysDepartService;
import com.java.module.sys.service.dto.DepartQueryParamsDTO;
import com.java.module.sys.service.dto.DepartTreeDTO;
import com.java.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 部门接口
 *
 * @author: jcm
 * @date: 2020/09/14
 */
@RestController
@RequestMapping("/sys/depart")
@Api(tags = "系统：部门接口")
public class SysDepartController {

    private final SysDepartService departService;

    public SysDepartController(SysDepartService departService) {
        this.departService = departService;
    }

    @ApiOperation("菜单列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:depart:list')")
    public CommonResult<List<DepartTreeDTO>> list(DepartQueryParamsDTO params) {
        List<DepartTreeDTO> departs = departService.treeList(params);
        return CommonResult.success(departs);
    }

    @ApiOperation("导出菜单")
    @GetMapping(value = "/export")
    @PreAuthorize("hasAnyAuthority('admin', 'sys:depart:list')")
    public void export(DepartQueryParamsDTO params, HttpServletResponse response) {
        List<DepartTreeDTO> departs = departService.treeListSort(params);
        ExcelUtils.downLoad(departs, DepartTreeDTO.class, response);
    }

    @ApiOperation("菜单列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<DepartTreeDTO>> listAll() {
        List<DepartTreeDTO> departs = departService.treeList(null);
        return CommonResult.success(departs);
    }

    @ApiOperation("添加菜单")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:depart:add')")
    public CommonResult<String> create(@Validated(SysDepart.Create.class) @RequestBody SysDepart sysDepart) {
        departService.create(sysDepart);
        return CommonResult.success("添加成功");
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:depart:update')")
    public CommonResult<String> update(@Validated(SysDepart.Update.class) @RequestBody SysDepart sysDepart) {
        departService.update(sysDepart);
        return CommonResult.success("修改成功");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:depart:delete')")
    public CommonResult<String> delete(@RequestBody List<Long> ids) {
        departService.delete(ids);
        return CommonResult.success("删除成功");
    }

}
