package com.java.module.sys.service;

import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.module.sys.dto.ListItemDTO;
import com.java.module.sys.dto.query.ListItemQueryDTO;
import com.java.module.sys.model.SysListItem;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public interface SysListItemService {

    /**
     * 分页查询
     *
     * @param params   .
     * @param pageSort .
     * @return .
     */
    CommonPage<ListItemDTO> page(ListItemQueryDTO params, CommonQueryPageSort pageSort);

    /**
     * 根据 listId 获取 listItem
     *
     * @param listId .
     * @return .
     */
    List<SysListItem> findListByListId(Long listId);

    /**
     * 根据列选项类型获取值
     *
     * @param type     .
     * @param itemList .
     * @return .
     */
    String getValueByType(String type, List<SysListItem> itemList);

    /**
     * 根据列选项值获取类型
     *
     * @param value    .
     * @param itemList .
     * @return .
     */
    String getTypeByValue(String value, List<SysListItem> itemList);

    /**
     * 根据listIds 查询记录数
     *
     * @param listIds .
     * @return .
     */
    int countByListIds(List<Long> listIds);

    /**
     * 添加菜单
     *
     * @param sysListItem .
     */
    void create(SysListItem sysListItem);

    /**
     * 修改菜单
     *
     * @param sysListItem .
     */
    void update(SysListItem sysListItem);

    /**
     * 删除菜单
     *
     * @param ids .
     */
    void delete(List<Long> ids);

}
