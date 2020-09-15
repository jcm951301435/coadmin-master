package com.java.module.sys.action;

import com.java.model.BaseEntity;
import com.java.model.CommonResult;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.module.sys.service.dto.ListItemQueryParamsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 列选项接口
 *
 * @author: jcm
 * @date: 2020/09/14
 */
@RestController
@RequestMapping("/sys/list/item")
@Api(tags = "系统：列选项接口")
public class SysListItemController {

    private final SysListItemService listItemService;

    public SysListItemController(SysListItemService listItemService) {
        this.listItemService = listItemService;
    }

    @ApiOperation("菜单列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:list')")
    public CommonResult<List<SysListItem>> list(ListItemQueryParamsDTO params) {
        Long listId = params.getListId();
        if (listId == null) {
            return CommonResult.success(null);
        }
        List<SysListItem> sysListItems = listItemService.findListByListId(listId);
        return CommonResult.success(sysListItems);
    }

    @ApiOperation("添加菜单")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:add')")
    public CommonResult<String> create(@Validated(BaseEntity.Create.class) @RequestBody SysListItem sysListItem) {
        int result = listItemService.create(sysListItem);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failure("添加失败");
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:update')")
    public CommonResult<String> update(@Validated(BaseEntity.Update.class) @RequestBody SysListItem sysListItem) {
        int result = listItemService.update(sysListItem);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failure("修改失败");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:delete')")
    public CommonResult<String> delete(@RequestBody List<Long> ids) {
        listItemService.delete(ids);
        return CommonResult.success("删除成功");
    }

}
