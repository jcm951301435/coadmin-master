package com.java.module.sys.action.vo;

import java.io.Serializable;

/**
 * 路由内容
 *
 * @author: jcm
 * @date: 2020/08/20
 */
public class MenuMetaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "MenuMetaVO{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
