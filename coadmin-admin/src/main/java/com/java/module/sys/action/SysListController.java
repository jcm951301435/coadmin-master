package com.java.module.sys.action;

import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.model.CommonResult;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.dto.ListDTO;
import com.java.module.sys.dto.query.ListQueryDTO;
import com.java.module.sys.model.SysList;
import com.java.module.sys.service.SysListService;
import com.java.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 列选接口
 *
 * @author: jcm
 * @date: 2020/09/14
 */
@RestController
@RequestMapping("/sys/list")
@Api(tags = "系统：列选接口")
public class SysListController {

    private final SysListService listService;

    public SysListController(SysListService listService) {
        this.listService = listService;
    }

    @ApiOperation("列选列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:list')")
    public CommonResult<CommonPage<ListDTO>> list(ListQueryDTO params, CommonQueryPageSort pageSort) {
        CommonPage<ListDTO> page = listService.page(params, pageSort);
        return CommonResult.success(page);
    }

    @ApiOperation("导出列选")
    @GetMapping(value = "/export")
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:list')")
    public void export(ListQueryDTO params, HttpServletResponse response) {
        List<ListExportVO> sysLists = listService.listExport(params);
        ExcelUtils.downLoad(sysLists, ListExportVO.class, response);
    }

    @ApiOperation("添加列选")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:add')")
    public CommonResult<String> create(@Validated(SysList.Create.class) @RequestBody SysList sysList) {
        listService.create(sysList);
        return CommonResult.success("添加成功");
    }

    @ApiOperation("修改列选")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:update')")
    public CommonResult<String> update(@Validated(SysList.Update.class) @RequestBody SysList sysList) {
        listService.update(sysList);
        return CommonResult.success("修改成功");
    }

    @ApiOperation("删除列选")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin', 'sys:list:delete')")
    public CommonResult<String> delete(@RequestBody List<Long> ids) {
        listService.delete(ids);
        return CommonResult.success("删除成功");
    }

}
