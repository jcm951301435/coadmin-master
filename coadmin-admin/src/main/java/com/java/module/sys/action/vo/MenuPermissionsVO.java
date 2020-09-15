package com.java.module.sys.action.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 前端路由
 *
 * @author: jcm
 * @date: 2020/08/20
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuPermissionsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Integer type;

    private MenuMetaVO meta;

    private List<MenuPermissionsVO> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public MenuMetaVO getMeta() {
        return meta;
    }

    public void setMeta(MenuMetaVO meta) {
        this.meta = meta;
    }

    public List<MenuPermissionsVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuPermissionsVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", hidden=" + hidden +
                ", redirect='" + redirect + '\'' +
                ", component='" + component + '\'' +
                ", type=" + type +
                ", meta=" + meta +
                ", children=" + children +
                '}';
    }

}
