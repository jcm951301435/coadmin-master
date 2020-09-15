package com.java.module.sys.service;

import com.java.module.sys.model.SysListItem;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public interface SysListItemService {

    /**
     * 根据 listId 获取 listItem
     * @param listId .
     * @return .
     */
    List<SysListItem> findListByListId(Long listId);

    /**
     * 根据列选项类型获取值
     * @param type
     * @param itemList
     * @return
     */
    String getValueByType(String type, List<SysListItem> itemList);

    /**
     * 根据列选项值获取类型
     * @param value .
     * @param itemList .
     * @return .
     */
    String getTypeByValue(String value, List<SysListItem> itemList);

    /**
     * 添加菜单
     *
     * @param sysListItem .
     * @return .
     */
    int create(SysListItem sysListItem);

    /**
     * 修改菜单
     *
     * @param sysListItem .
     * @return .
     */
    int update(SysListItem sysListItem);

    /**
     * 删除菜单
     *
     * @param ids .
     * @return .
     */
    int delete(List<Long> ids);

}
