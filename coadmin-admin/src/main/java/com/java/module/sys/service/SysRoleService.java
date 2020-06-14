package com.java.module.sys.service;

import com.java.module.sys.model.SysRole;

import java.util.List;

/**
 * 角色业务接口
 *
 * @author: jcm
 * @date: 2020/06/14
 */
public interface SysRoleService {

    /**
     * 根据用户编号查询角色列表
     *
     * @param userId .
     * @return .
     */
    List<SysRole> listByUserId(Long userId);

}
