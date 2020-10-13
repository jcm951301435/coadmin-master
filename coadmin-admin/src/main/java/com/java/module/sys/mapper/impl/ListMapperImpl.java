package com.java.module.sys.mapper.impl;

import com.java.model.CommonPage;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.dto.ListDTO;
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
    public ListDTO toDTO(SysList entity) {
        if (entity == null) {
            return null;
        }
        ListDTO dto = new ListDTO();
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
    public List<ListDTO> toDTO(List<SysList> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<ListDTO> exports = new ArrayList<>();
        for (SysList sysList : list) {
            exports.add(toDTO(sysList));
        }
        return exports;
    }

    @Override
    public CommonPage<ListDTO> toDTO(CommonPage<SysList> page) {
        if (page == null) {
            return new CommonPage<>();
        }
        CommonPage<ListDTO> resultPage = new CommonPage<>();
        resultPage.setPageNum(page.getPageNum());
        resultPage.setPageSize(page.getPageSize());
        resultPage.setTotal(page.getTotal());
        resultPage.setTotalPage(page.getTotalPage());
        resultPage.setList(toDTO(page.getList()));
        return resultPage;
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
