package com.java.util;


import com.java.model.BaseTreeNode;

import java.util.*;
import java.util.stream.Collectors;

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
        List<T> parents = getParentList(list);
        for (T t : parents) {
            t.setChildren(findChildList(t.getId(), list));
        }
        return parents;
    }

    /**
     * 获取最上级
     *
     * @param list .
     * @param <T>  .
     * @return .
     */
    private static <T extends BaseTreeNode<T>> List<T> getParentList(List<T> list) {
        Map<Long, T> map = list.stream().collect(Collectors.toMap(BaseTreeNode::getId, t -> t));
        List<T> parents = new ArrayList<>();
        Set<Long> idSet = new HashSet<>();
        for (T t : list) {
            T temp = getTopEntity(t, map);
            if (idSet.contains(temp.getId())) {
                continue;
            }
            idSet.add(temp.getId());
            parents.add(temp);
        }
        return parents;
    }

    /**
     * 获取最上级
     *
     * @param t .
     * @param map .
     * @param <T> .
     * @return .
     */
    private static <T extends BaseTreeNode<T>> T getTopEntity(T t, Map<Long, T> map) {
        T top = t;
        if (map.containsKey(t.getPid())) {
            top = getTopEntity(map.get(t.getPid()), map);
        }
        return top;
    }

    /**
     * 获取树排序后的列表
     *
     * @param beforeTree 必须为树，否则获取不到结果
     * @param result     .
     * @param <T>        .
     * @return .
     */
    public static <T extends BaseTreeNode<T>> List<T> sort(List<T> beforeTree, List<T> result) {
        if (CollectionUtils.isEmpty(beforeTree)) {
            return beforeTree;
        }
        if (result == null) {
            result = new ArrayList<>();
        }
        for (T menuTree : beforeTree) {
            result.add(menuTree);
            if (CollectionUtils.isNotEmpty(menuTree.getChildren())) {
                result = sort(menuTree.getChildren(), result);
            }
        }
        return result;
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
        if (CollectionUtils.isEmpty(list)) {
            return childList;
        }
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
                entity.setChildren(childListTemp);
                childList.add(entity);
            }
        }
        return childList;
    }

}
