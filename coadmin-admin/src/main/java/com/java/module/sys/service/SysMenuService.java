package com.java.module.sys.service;

import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.action.vo.MenuPermissionsVO;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.dto.MenuListParamsDTO;
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
     * 根据用户查询菜单
     *
     * @param userDetails .
     * @return .
     */
    List<MenuPermissionsVO> buildMenuVO(SecurityUserDetails userDetails);

    /**
     * 根据用户查询菜单
     *
     * @param userDetails .
     * @return .
     */
    List<MenuTreeDTO> listByUser(SecurityUserDetails userDetails);

    /**
     * 根据角色编号数组查询菜单
     *
     * @param roleIds .
     * @return .
     */
    List<SysMenu> listByRoleIds(Long[] roleIds);

    /**
     * 查询菜单 树结构
     *
     * @param params .
     * @return .
     */
    List<MenuTreeDTO> treeList(MenuListParamsDTO params);

    /**
     * 排序树菜单
     * @param params
     * @return
     */
    List<MenuTreeDTO> treeListSort(MenuListParamsDTO params);

    /**
     * 添加菜单
     *
     * @param sysMenu .
     * @return .
     */
    int create(SysMenu sysMenu);

    /**
     * 修改菜单
     *
     * @param sysMenu .
     * @return .
     */
    int update(SysMenu sysMenu);

    /**
     * 删除菜单
     *
     * @param ids .
     * @return .
     */
    int delete(List<Long> ids);

}
