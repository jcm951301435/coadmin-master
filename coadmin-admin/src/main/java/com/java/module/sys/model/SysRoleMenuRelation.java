package com.java.module.sys.model;

import com.java.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_role_menu_relation 角色-权限关系
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenuRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 权限编号
     */
    private Long menuId;

}