package com.java.module.sys.service;

import com.java.module.sys.model.SysDepart;
import com.java.module.sys.service.dto.DepartQueryParamsDTO;
import com.java.module.sys.service.dto.DepartTreeDTO;

import java.util.List;

/**
 * 部门业务接口
 *
 * @author: jcm
 * @date: 2020/06/11
 */
public interface SysDepartService {

    /**
     * 查询部门 树结构
     *
     * @param params .
     * @return .
     */
    List<DepartTreeDTO> treeList(DepartQueryParamsDTO params);

    /**
     * 排序树菜单
     * @param params
     * @return
     */
    List<DepartTreeDTO> treeListSort(DepartQueryParamsDTO params);

    /**
     * 添加部门
     *
     * @param sysDepart .
     * @return .
     */
    int create(SysDepart sysDepart);

    /**
     * 修改部门
     *
     * @param sysDepart .
     * @return .
     */
    int update(SysDepart sysDepart);

    /**
     * 删除部门
     *
     * @param ids .
     * @return .
     */
    int delete(List<Long> ids);

}
