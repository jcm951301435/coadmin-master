package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * sys_menu 权限
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snow-flake-id")
    @GenericGenerator(name = "snow-flake-id", strategy = "com.java.config.SnowFlakeIdGenerator")
    @NotNull(groups = Update.class, message = "id 不能为空")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title")
    @NotBlank(groups = Create.class, message = "菜单标题不能为空")
    private String title;

    /**
     * 组件名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 路由路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 权限值
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 组件路径
     */
    @Column(name = "component_url")
    private String componentUrl;

    /**
     * 父级主键
     */
    @Column(name = "pid")
    @NotNull(groups = Update.class, message = "上级菜单不能为空")
    private Long pid;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 是否隐藏
     */
    @Column(name = "hidden")
    private Boolean hidden;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Long sort;

    @ManyToMany(mappedBy = "menus")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<SysRole> roles;

}