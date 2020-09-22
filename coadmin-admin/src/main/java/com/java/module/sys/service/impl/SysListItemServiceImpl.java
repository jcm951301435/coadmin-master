package com.java.module.sys.service.impl;

import com.java.module.sys.dao.SysListItemRepository;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.util.CollectionUtils;
import com.java.util.StringUtils;
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

    private final SysListItemRepository itemRepository;

    public SysListItemServiceImpl(SysListItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<SysListItem> findListByListId(Long listId) {
//        QueryWrapper<SysListItem> itemWrapper = new QueryWrapper<>();
//        itemWrapper.eq("list_id", listId);
        return itemRepository.findAll();
    }

    @Override
    public String getValueByType(String type, List<SysListItem> itemList) {
        return getTypeOrValue(type, itemList, true);
    }

    @Override
    public String getTypeByValue(String value, List<SysListItem> itemList) {
        return getTypeOrValue(value, itemList, false);
    }

    @Override
    public int countByListIds(List<Long> listIds) {
        if (CollectionUtils.isEmpty(listIds)) {
            return 0;
        }
//        QueryWrapper<SysListItem> itemWrapper = new QueryWrapper<>();
//        itemWrapper.in("list_id", listIds);
//        return itemDao.selectCount(itemWrapper);
        return (int)itemRepository.count();
    }

    /**
     *
     * @param str type/value
     * @param itemList 源
     * @param useType 是否使用type; true: use type; false: use value
     * @return value/type
     */
    private String getTypeOrValue(String str, List<SysListItem> itemList, boolean useType) {
        if (StringUtils.isEmpty(str) || CollectionUtils.isEmpty(itemList)) {
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
    public int create(SysListItem sysListItem) {
        itemRepository.save(sysListItem);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysListItem sysListItem) {
        itemRepository.save(sysListItem);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(List<Long> ids) {
        return itemRepository.deleteByIdIn(ids);
    }

}
