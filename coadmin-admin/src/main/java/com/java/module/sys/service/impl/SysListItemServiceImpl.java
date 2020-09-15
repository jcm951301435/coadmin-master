package com.java.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.module.sys.dao.SysListItemDao;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.util.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysListItemServiceImpl implements SysListItemService {

    private final SysListItemDao itemDao;

    public SysListItemServiceImpl(SysListItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    @Cacheable(value = "SysListItemList", key = "#listId")
    public List<SysListItem> findListByListId(Long listId) {
        QueryWrapper<SysListItem> itemWrapper = new QueryWrapper<>();
        itemWrapper.eq("list_id", listId);
        return itemDao.selectList(itemWrapper);
    }

    @Override
    public String getValueByType(String type, List<SysListItem> itemList) {
        return getTypeOrValue(type, itemList, true);
    }

    @Override
    public String getTypeByValue(String value, List<SysListItem> itemList) {
        return getTypeOrValue(value, itemList, false);
    }

    /**
     *
     * @param str type/value
     * @param itemList 源
     * @param useType 是否使用type; true: use type; false: use value
     * @return value/type
     */
    private String getTypeOrValue(String str, List<SysListItem> itemList, boolean useType) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String result = null;
        for (SysListItem item : itemList) {
            String typeTemp = item.getType();
            String valueTemp = item.getValue();
            if (str.equals(useType ? typeTemp : valueTemp)) {
                result = useType ? item.getValue() : item.getType();
                break;
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "SysListItemList", key = "#sysListItem.listId", condition = "#result > 0")
    public int create(SysListItem sysListItem) {
        return itemDao.insert(sysListItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "SysListItemList", key = "#sysListItem.listId", condition = "#result > 0")
    public int update(SysListItem sysListItem) {
        return itemDao.updateById(sysListItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "SysListItemList", allEntries = true, condition = "#result > 0")
    public int delete(List<Long> ids) {
        return itemDao.deleteBatchIds(ids);
    }

}
