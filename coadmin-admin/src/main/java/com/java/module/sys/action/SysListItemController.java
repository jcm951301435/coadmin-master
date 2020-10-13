package com.java.module.sys.action;

import com.java.model.BaseEntity;
import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.model.CommonResult;
import com.java.module.sys.dto.ListItemDTO;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.module.sys.dto.query.ListItemQueryDTO;
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

    @ApiOperation("列选项列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:list')")
    public CommonResult<CommonPage<ListItemDTO>> list(ListItemQueryDTO params, CommonQueryPageSort pageSort) {
        CommonPage<ListItemDTO> page = listItemService.page(params, pageSort);
        return CommonResult.success(page);
    }

    @ApiOperation("添加列选项")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:add')")
    public CommonResult<String> create(@Validated(BaseEntity.Create.class) @RequestBody SysListItem sysListItem) {
        listItemService.create(sysListItem);
        return CommonResult.success("添加成功");
    }

    @ApiOperation("修改列选项")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:update')")
    public CommonResult<String> update(@Validated(BaseEntity.Update.class) @RequestBody SysListItem sysListItem) {
        listItemService.update(sysListItem);
        return CommonResult.success("修改成功");
    }

    @ApiOperation("删除列选项")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:delete')")
    public CommonResult<String> delete(@RequestBody List<Long> ids) {
        listItemService.delete(ids);
        return CommonResult.success("删除成功");
    }

}
