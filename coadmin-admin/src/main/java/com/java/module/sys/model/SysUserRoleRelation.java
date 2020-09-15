package com.java.module.sys.model;


import com.java.model.BaseEntity;

/**
 * sys_user_role_relation 用户-角色关系
 *
 * @author: jcm
 * @date: 2020/05/08
 */
public class SysUserRoleRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 角色主键
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}