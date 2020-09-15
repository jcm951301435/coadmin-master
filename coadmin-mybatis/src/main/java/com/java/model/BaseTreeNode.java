package com.java.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
public abstract class BaseTreeNode<T extends BaseTreeNode<T>> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private Long pid;

    private List<T> children;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "BaseTreeNode{" +
                "pid=" + pid +
                ", children=" + children +
                '}';
    }
}
