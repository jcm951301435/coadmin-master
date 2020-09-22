package com.java.module.sys.service;

import com.java.model.CommonPage;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.service.dto.RoleQueryParamsDTO;

import java.util.List;

/**
 * 角色业务接口
 *
 * @author: jcm
 * @date: 2020/06/14
 */
public interface SysRoleService {

    /**
     * 分页查询
     * @param params .
     * @param commonPage .
     * @return .
     */
    CommonPage<SysRole> page(RoleQueryParamsDTO params, CommonPage<SysRole> commonPage);

    /**
     * 查询列表
     *
     * @param params .
     * @return .
     */
    List<SysRole> list(RoleQueryParamsDTO params);

    /**
     * 添加
     *
     * @param sysRole .
     * @return .
     */
    int create(SysRole sysRole);

    /**
     * 修改
     *
     * @param sysRole .
     * @return .
     */
    int update(SysRole sysRole);

    /**
     * 删除
     *
     * @param ids .
     * @return .
     */
    int delete(List<Long> ids);

}
