package com.java.module.model.sys;

import com.java.module.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_role 角色
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
     * 备注
     */
    private String remark;

}