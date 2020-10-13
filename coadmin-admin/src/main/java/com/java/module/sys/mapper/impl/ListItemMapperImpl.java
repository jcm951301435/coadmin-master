package com.java.module.sys.mapper.impl;

import com.java.model.CommonPage;
import com.java.module.sys.dto.ListItemDTO;
import com.java.module.sys.mapper.ListItemMapper;
import com.java.module.sys.model.SysListItem;
import com.java.util.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Component
public class ListItemMapperImpl implements ListItemMapper {

    @Override
    public ListItemDTO toDTO(SysListItem entity) {
        if (entity == null) {
            return null;
        }
        ListItemDTO dto = new ListItemDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setValue(entity.getValue());
        dto.setCreateTime(entity.getCreateTime());
        dto.setCreateBy(entity.getCreateBy());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setUpdateBy(entity.getUpdateBy());
        return dto;
    }

    @Override
    public List<ListItemDTO> toDTO(List<SysListItem> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<ListItemDTO> items = new ArrayList<>();
        for (SysListItem item : list) {
            items.add(toDTO(item));
        }
        return items;
    }

    @Override
    public CommonPage<ListItemDTO> toDTO(CommonPage<SysListItem> page) {
        if (page == null) {
            return new CommonPage<>();
        }
        CommonPage<ListItemDTO> resultPage = new CommonPage<>();
        resultPage.setPageNum(page.getPageNum());
        resultPage.setPageSize(page.getPageSize());
        resultPage.setTotal(page.getTotal());
        resultPage.setTotalPage(page.getTotalPage());
        resultPage.setList(toDTO(page.getList()));
        return resultPage;
    }

}
