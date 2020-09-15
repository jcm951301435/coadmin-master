package com.java.module.sys.mapper;

import com.java.module.sys.action.vo.MenuPermissionsVO;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.dto.MenuTreeDTO;

import java.util.List;

/**
 * menu 相关 转换
 *
 * @author: jcm
 * @date: 2020/09/11
 */
public interface MenuMapper {

    /**
     * SysMenu -> MenuTree
     *
     * @param menu .
     * @return .
     */
    MenuTreeDTO toMenuTree(SysMenu menu);

    /**
     * SysMenu -> MenuTree
     *
     * @param menus .
     * @return .
     */
    List<MenuTreeDTO> toMenuTree(List<SysMenu> menus);

    /**
     * 构造前端路由
     * @param menuTreeDTOList .
     * @return .
     */
    List<MenuPermissionsVO> buildMenuVO(List<MenuTreeDTO> menuTreeDTOList);

}
