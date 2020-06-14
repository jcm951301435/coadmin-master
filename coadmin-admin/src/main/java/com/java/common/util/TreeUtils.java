package com.java.common.util;

import com.java.common.model.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树工具类
 *
 * @author: jcm
 * @date: 2020/06/13
 */
public class TreeUtils {

    /**
     * 获取树型结构
     *
     * @param list .
     * @return .
     */
    public static <T extends BaseTreeNode<T>> List<T> getTreeList(List<T> list) {
        return findChildList(0L, list);
    }

    /**
     * 获取下级
     *
     * @param pid  .
     * @param list .
     * @return .
     */
    private static <T extends BaseTreeNode<T>> List<T> findChildList(Long pid, List<T> list) {
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
