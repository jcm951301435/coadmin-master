package com.java.module.sys.mapper.impl;

import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.mapper.ListMapper;
import com.java.module.sys.model.SysList;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.dto.SysListDTO;
import com.java.util.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Component
public class ListMapperImpl implements ListMapper {

    @Override
    public SysListDTO toListDTO(SysList entity) {
        SysListDTO sysListDTO = new SysListDTO();
        if (entity == null) {
            return null;
        }
        sysListDTO.setId(entity.getId());
        sysListDTO.setType(entity.getType());
        sysListDTO.setValue(entity.getValue());
        sysListDTO.setCreateTime(entity.getCreateTime());
        sysListDTO.setCreateBy(entity.getCreateBy());
        sysListDTO.setUpdateTime(entity.getUpdateTime());
        sysListDTO.setUpdateBy(entity.getUpdateBy());
        return sysListDTO;
    }

    @Override
    public SysListDTO toListDTO(SysList entity, List<SysListItem> itemList) {
        SysListDTO sysListDTO = toListDTO(entity);
        if (sysListDTO == null) {
            return null;
        }
        if (CollectionUtils.isNotEmpty(itemList)) {
            sysListDTO.setItems(itemList);
        }
        return sysListDTO;
    }

    @Override
    public List<ListExportVO> toExportVO(List<SysListDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<ListExportVO> exports = new ArrayList<>();
        for (SysListDTO dto : list) {
            List<SysListItem> items =  dto.getItems();
            for (SysListItem item : items) {
                ListExportVO exportVO = new ListExportVO();
                exportVO.setId(dto.getId());
                exportVO.setType(dto.getType());
                exportVO.setValue(dto.getValue());
                exportVO.setItemId(item.getId());
                exportVO.setItemType(item.getType());
                exportVO.setItemValue(item.getValue());
                exports.add(exportVO);
            }
        }
        return exports;
    }

}
