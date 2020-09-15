package com.java.module.sys.service;

import com.java.model.CommonPage;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.model.SysList;
import com.java.module.sys.service.dto.ListQueryParamsDTO;
import com.java.module.sys.service.dto.SysListDTO;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public interface SysListService {

    /**
     * 分页查询
     * @param params .
     * @param commonPage .
     * @return .
     */
    CommonPage<SysList> page(ListQueryParamsDTO params, CommonPage<SysList> commonPage);

    /**
     * 根据类型获取列选DTO
     *
     * @param type .
     * @return .
     */
    SysListDTO findOneByType(String type);

    /**
     * 获取导出列表
     * @param params .
     * @return .
     */
    List<ListExportVO> listExport(ListQueryParamsDTO params);

    /**
     * 查询列表
     *
     * @param params .
     * @return .
     */
    List<SysList> list(ListQueryParamsDTO params);

    /**
     * 添加菜单
     *
     * @param sysList .
     * @return .
     */
    int create(SysList sysList);

    /**
     * 修改菜单
     *
     * @param sysList .
     * @return .
     */
    int update(SysList sysList);

    /**
     * 删除菜单
     *
     * @param ids .
     * @return .
     */
    int delete(List<Long> ids);


}
