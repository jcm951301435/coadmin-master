package com.java.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.sys.model.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 根据用户编号查询角色列表
     *
     * @param userId .
     * @return .
     */
    List<SysRole> listByUserId(Long userId);

}