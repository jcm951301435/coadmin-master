package com.java.module.sys.model;

import com.java.model.BaseEntity;

/**
 * sys_role 角色
 *
 * @author: jcm
 * @date: 2020/05/08
 */
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 数据权限类型 0:全部 1:仅本级 2:自定义
     */
    private Integer scopeType;

    /**
     * 权限值
     */
    private String permission;

    /**
     * 备注
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}