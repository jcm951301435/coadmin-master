package com.java.module.sys.mapper;

import com.java.model.CommonPage;
import com.java.module.sys.dto.ListItemDTO;
import com.java.module.sys.model.SysListItem;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public interface ListItemMapper {

    /**
     * SysListItem > ListItemDTO
     *
     * @param entity .
     * @return .
     */
    ListItemDTO toDTO(SysListItem entity);

    /**
     * SysListItem > ListItemDTO
     *
     * @param list .
     * @return .
     */
    List<ListItemDTO> toDTO(List<SysListItem> list);

    /**
     * SysListItem > ListItemDTO
     *
     * @param page .
     * @return .
     */
    CommonPage<ListItemDTO> toDTO(CommonPage<SysListItem> page);

}
