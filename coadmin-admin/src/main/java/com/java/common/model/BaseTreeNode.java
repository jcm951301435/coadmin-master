package com.java.common.model;

import com.java.common.util.CollectionUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
@Data
public abstract class BaseTreeNode<T extends BaseTreeNode<T>> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pid;

    private List<T> childList;

    /**
     * 是否有下级
     * @return
     */
    public boolean hasChild() {
        return CollectionUtils.isNotEmpty(childList);
    }

    /**
     * 获取树型结构
     * @param list
     * @return
     */
    public List<T> getTreeList(List<T> list) {
        return findChildList(0L, list);
    }

    /**
     * 获取下级
     * @param pid
     * @param list
     * @return
     */
    private List<T> findChildList(Long pid, List<T> list) {
        List<T> childList = new ArrayList<>();
        for (T entity : list) {
            Long pidTemp = entity.getPid();
            boolean isChild;
            if (pid == null) {
                isChild = pidTemp == null;
            } else {
                isChild = pid.equals(pidTemp);
            }
            if (isChild) {
                List<T> childListTemp = findChildList(entity.getId(), list);
                entity.setChildList(childListTemp);
                childList.add(entity);
            }
        }
        return childList;
    }

}
