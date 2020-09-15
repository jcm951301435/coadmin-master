package com.java.module.sys.service.dto;


import com.java.annotation.ExcelBook;
import com.java.annotation.ExcelColumn;
import com.java.model.BaseTreeNode;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
@ExcelBook(title = "菜单列表")
public class MenuTreeDTO extends BaseTreeNode<MenuTreeDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @ExcelColumn(text = "菜单标题", sort = 1)
    private String title;

    /**
     * 组件名称
     */
    @ExcelColumn(text = "组件名称", sort = 2)
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类型
     */
    @ExcelColumn(text = "组件类型", sort = 3)
    private String typeStr;

    /**
     * 权限值
     */
    @ExcelColumn(text = "权限值", sort = 4)
    private String permission;

    /**
     * 路由路径
     */
    @ExcelColumn(text = "路由路径", sort = 5)
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 组件路径
     */
    @ExcelColumn(text = "组件路径", sort = 6)
    private String componentUrl;

    /**
     * 是否隐藏
     */
    @ExcelColumn(text = "是否可见", sort = 6)
    private String canSee;

    /**
     * 排序
     */
    @ExcelColumn(text = "排序", sort = 7)
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

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getCanSee() {
        return canSee;
    }

    public void setCanSee(String canSee) {
        this.canSee = canSee;
    }

    @Override
    public String toString() {
        return "MenuTreeDTO{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", typeStr='" + typeStr + '\'' +
                ", permission='" + permission + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", hidden=" + hidden +
                ", componentUrl='" + componentUrl + '\'' +
                ", canSee='" + canSee + '\'' +
                ", sort=" + sort +
                ", sort=" + sort +
                '}';
    }
}
