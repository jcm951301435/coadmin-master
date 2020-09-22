package com.java.module.sys.dao;


import com.java.module.sys.model.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu, Long>, JpaSpecificationExecutor<SysMenu> {

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

    /**
     * 根据 roleIds 查询 sysMenu
     * @param roleIds .
     * @return .
     */
    List<SysMenu> findByRoles_IdIn(Long[] roleIds);

    /**
     * 根据角色编号数组查询菜单
     *
     * @param roleIds .
     * @return .
     */
//    List<SysMenu> listByRoleIds(Long[] roleIds);

}