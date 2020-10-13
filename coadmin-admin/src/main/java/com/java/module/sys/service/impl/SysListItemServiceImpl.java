package com.java.module.sys.service.impl;

import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.module.sys.dao.SysListItemRepository;
import com.java.module.sys.dto.ListItemDTO;
import com.java.module.sys.dto.query.ListItemQueryDTO;
import com.java.module.sys.mapper.ListItemMapper;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.util.CollectionUtils;
import com.java.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysListItemServiceImpl implements SysListItemService {

    private final SysListItemRepository itemRepository;

    private final ListItemMapper itemMapper;

    public SysListItemServiceImpl(SysListItemRepository itemRepository, ListItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public CommonPage<ListItemDTO> page(ListItemQueryDTO params, CommonQueryPageSort pageSort) {
        CommonPage<SysListItem> page = itemRepository.findAll(params.toSpecification(), pageSort);
        return itemMapper.toDTO(page);
    }

    @Override
    public List<SysListItem> findListByListId(Long listId) {
        return itemRepository.findByListId(listId);
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
        long count = itemRepository.count((Specification<SysListItem>) (root, query, cb) -> {
            Expression<Long> expression = root.join("list").get("id");
            return expression.in(listIds);
        });
        return (int) count;
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
    public void create(SysListItem sysListItem) {
        itemRepository.save(sysListItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysListItem sysListItem) {
        itemRepository.save(sysListItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        itemRepository.deleteByIdIn(ids);
    }

}
