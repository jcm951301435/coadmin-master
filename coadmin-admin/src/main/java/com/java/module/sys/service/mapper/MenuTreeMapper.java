package com.java.module.sys.service.mapper;

import com.java.common.model.BaseMapper;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.dto.MenuTreeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * MenuTreeDTO <=> SysMenu
 *
 * @author: jcm
 * @date: 2020/06/13
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuTreeMapper extends BaseMapper<MenuTreeDTO, SysMenu> {
}
