package com.java.module.model.sys;

import com.java.module.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_permission 权限
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 权限值
     */
    private String value;

    /**
     * 路径
     */
    private String url;

    /**
     * 父级主键
     */
    private Long pid;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 排序
     */
    private Long sort;

}