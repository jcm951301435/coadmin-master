package com.java.module.model.sys;

import com.java.module.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_role_permission_relation 角色-权限关系
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRolePermissionRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 权限编号
     */
    private Long permissionId;

}