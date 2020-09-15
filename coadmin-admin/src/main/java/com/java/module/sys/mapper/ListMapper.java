package com.java.module.sys.mapper;

import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.model.SysList;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.dto.SysListDTO;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public interface ListMapper {

    /**
     * SysList -> SysListDTO
     * @param entity .
     * @return .
     */
    SysListDTO toListDTO(SysList entity);

    /**
     * SysList -> SysListDTO
     * @param entity .
     * @param itemList .
     * @return .
     */
    SysListDTO toListDTO(SysList entity, List<SysListItem> itemList);

    /**
     * SysListDTO -> ListExportVO
     * @param sysListDTO .
     * @return .
     */
    List<ListExportVO> toExportVO(List<SysListDTO> list);

}
