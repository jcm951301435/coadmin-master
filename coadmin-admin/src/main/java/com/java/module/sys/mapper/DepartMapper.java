package com.java.module.sys.mapper;

import com.java.module.sys.model.SysDepart;
import com.java.module.sys.service.dto.DepartTreeDTO;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
public interface DepartMapper {

    /**
     * SysDepart -> DepartTreeDTO
     *
     * @param depart .
     * @return .
     */
    DepartTreeDTO toDepartTree(SysDepart depart);

    /**
     * SysDepart -> MenuTreeDTO list
     *
     * @param departs .
     * @return .
     */
    List<DepartTreeDTO> toDepartTree(List<SysDepart> departs);

}
