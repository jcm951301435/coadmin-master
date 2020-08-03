package com.java.common.model;

import com.java.common.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
public abstract class BaseTreeNode<T extends BaseTreeNode<T>> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pid;

    private List<T> childList;

    /**
     * 是否有下级
     * @return .
     */
    public boolean hasChild() {
        return CollectionUtils.isNotEmpty(childList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
    }
}
