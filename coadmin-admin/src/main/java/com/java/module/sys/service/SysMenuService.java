package com.java.module.sys.service;

import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.dto.MenuTreeDTO;

import java.util.List;

/**
 * 菜单业务接口
 *
 * @author: jcm
 * @date: 2020/06/11
 */
public interface SysMenuService {

    /**
     * 根据角色编号数组查询菜单
     *
     * @param roleIds .
     * @return .
     */
    List<SysMenu> listByRoleIds(Long[] roleIds);

    /**
     * 构造菜单树
     *
     * @param menuList .
     * @return .
     */
    List<MenuTreeDTO> getMenuTree(List<SysMenu> menuList);

}
