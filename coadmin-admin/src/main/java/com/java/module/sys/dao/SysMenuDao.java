package com.java.module.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.sys.model.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysMenuDao extends BaseMapper<SysMenu> {

    /**
     * 根据角色编号数组查询菜单
     *
     * @param roleIds .
     * @return .
     */
    List<SysMenu> listByRoleIds(Long[] roleIds);

}