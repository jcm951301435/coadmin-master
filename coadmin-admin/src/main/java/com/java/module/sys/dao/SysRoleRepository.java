package com.java.module.sys.dao;

import com.java.module.sys.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor<SysRole> {

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

    /**
     * 根据用户主键获取roles
     * @param userId .
     * @return .
     */
    List<SysRole> findByUsersId(Long userId);

    /**
     * 根据用户编号查询角色列表
     *
     * @param userId .
     * @return .
     */
//    List<SysRole> listByUserId(Long userId);

    /**
     * 角色 - 部门、菜单
     *
     * @param params .
     * @return roleDTO
     */
//    List<RoleDTO> listRole(@Param("params") RoleQueryParamsDTO params);

}