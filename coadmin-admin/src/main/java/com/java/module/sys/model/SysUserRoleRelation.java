package com.java.module.sys.model;

import com.java.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_user_role_relation 用户-角色关系
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
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

}