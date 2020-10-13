package com.java.module.sys.service;

import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.dto.ListDTO;
import com.java.module.sys.dto.query.ListQueryDTO;
import com.java.module.sys.model.SysList;
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
     * @param pageSort .
     * @return .
     */
    CommonPage<ListDTO> page(ListQueryDTO params, CommonQueryPageSort pageSort);

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
    List<ListExportVO> listExport(ListQueryDTO params);

    /**
     * 添加菜单
     *
     * @param sysList .
     */
    void create(SysList sysList);

    /**
     * 修改菜单
     *
     * @param sysList .
     */
    void update(SysList sysList);

    /**
     * 删除菜单
     *
     * @param ids .
     */
    void delete(List<Long> ids);


}
