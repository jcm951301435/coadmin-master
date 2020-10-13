package com.java.module.sys.mapper;

import com.java.model.CommonPage;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.dto.ListDTO;
import com.java.module.sys.model.SysList;
import com.java.module.sys.service.dto.SysListDTO;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public interface ListMapper {

    /**
     * list > ListDTO
     *
     * @param entity .
     * @return .
     */
    ListDTO toDTO(SysList entity);

    /**
     * list > ListDTO
     *
     * @param list .
     * @return .
     */
    List<ListDTO> toDTO(List<SysList> list);

    /**
     * list > ListDTO
     *
     * @param page .
     * @return .
     */
    CommonPage<ListDTO> toDTO(CommonPage<SysList> page);

    /**
     * SysListDTO -> ListExportVO
     *
     * @param list .
     * @return .
     */
    List<ListExportVO> toExportVO(List<SysListDTO> list);

}
