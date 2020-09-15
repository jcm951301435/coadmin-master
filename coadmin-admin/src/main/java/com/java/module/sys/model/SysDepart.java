package com.java.module.sys.model;

import com.java.model.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 部门
 *
 * @author: jcm
 * @date: 2020/09/14
 */
public class SysDepart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(groups = Update.class, message = "id 不能为空")
    @Null(groups = Create.class, message = "id 必须为空")
    private Long id;

    /**
     * 部门名称
     */
    @NotBlank(groups = Create.class, message = "菜单标题不能为空")
    private String name;

    /**
     * 上级主键
     */
    @NotNull(groups = Update.class, message = "上级部门不能为空")
    private Long pid;

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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysDepart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", sort=" + sort +
                '}';
    }
}