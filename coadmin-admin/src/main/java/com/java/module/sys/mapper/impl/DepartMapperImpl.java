package com.java.module.sys.mapper.impl;
import com.java.module.sys.mapper.DepartMapper;
import com.java.module.sys.model.SysDepart;
import com.java.module.sys.service.dto.DepartTreeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
@Component
public class DepartMapperImpl implements DepartMapper {

    @Override
    public DepartTreeDTO toDepartTree(SysDepart depart) {
        if (depart == null) {
            return null;
        }
        DepartTreeDTO departTree = new DepartTreeDTO();
        departTree.setName(depart.getName());
        departTree.setSort(depart.getSort());
        departTree.setPid(depart.getPid());
        departTree.setId(depart.getId());
        departTree.setCreateTime(depart.getCreateTime());
        departTree.setCreateBy(depart.getCreateBy());
        departTree.setUpdateTime(depart.getUpdateTime());
        departTree.setUpdateBy(depart.getUpdateBy());
        return departTree;
    }

    @Override
    public List<DepartTreeDTO> toDepartTree(List<SysDepart> departs) {
        if (departs == null) {
            return null;
        }
        List<DepartTreeDTO> list = new ArrayList<>(departs.size());
        for (SysDepart sysDepart : departs) {
            list.add(toDepartTree(sysDepart));
        }
        return list;
    }

}
