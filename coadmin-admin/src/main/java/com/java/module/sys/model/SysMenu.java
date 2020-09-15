package com.java.module.sys.model;


import com.java.model.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * sys_menu 权限
 *
 * @author: jcm
 * @date: 2020/05/08
 */

public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(groups = Update.class, message = "id 不能为空")
    @Null(groups = Create.class, message = "id 必须为空")
    private Long id;

    /**
     * 标题
     */
    @NotBlank(groups = Create.class, message = "菜单标题不能为空")
    private String title;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 权限值
     */
    private String permission;

    /**
     * 组件路径
     */
    private String componentUrl;

    /**
     * 父级主键
     */
    @NotNull(groups = Update.class, message = "上级菜单不能为空")
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getComponentUrl() {
        return componentUrl;
    }

    public void setComponentUrl(String componentUrl) {
        this.componentUrl = componentUrl;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", permission='" + permission + '\'' +
                ", componentUrl='" + componentUrl + '\'' +
                ", pid=" + pid +
                ", icon='" + icon + '\'' +
                ", hidden=" + hidden +
                ", sort=" + sort +
                '}';
    }
}