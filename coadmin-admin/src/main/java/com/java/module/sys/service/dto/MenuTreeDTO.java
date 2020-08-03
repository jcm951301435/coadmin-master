package com.java.module.sys.service.dto;

import com.java.common.model.BaseTreeNode;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
public class MenuTreeDTO extends BaseTreeNode<MenuTreeDTO> {

    private static final long serialVersionUID = 1L;

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
    private String permission;

    /**
     * 组件路径
     */
    private String componentUrl;

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
        return "MenuTreeDTO{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", componentUrl='" + componentUrl + '\'' +
                ", icon='" + icon + '\'' +
                ", hidden=" + hidden +
                ", sort=" + sort +
                '}';
    }
}
