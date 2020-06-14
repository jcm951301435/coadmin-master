package com.java.module.sys.service.impl;

import com.java.common.util.TreeUtils;
import com.java.module.sys.dao.SysMenuDao;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.SysMenuService;
import com.java.module.sys.service.dto.MenuTreeDTO;
import com.java.module.sys.service.mapper.MenuTreeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务接口实现
 *
 * @author: jcm
 * @date: 2020/06/11
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuDao menuDao;

    private final MenuTreeMapper menuTreeMapper;

    public SysMenuServiceImpl(SysMenuDao menuDao, MenuTreeMapper menuTreeMapper) {
        this.menuDao = menuDao;
        this.menuTreeMapper = menuTreeMapper;
    }

    @Override
    public List<SysMenu> listByRoleIds(Long[] roleIds) {
        return menuDao.listByRoleIds(roleIds);
    }

    @Override
    public List<MenuTreeDTO> getMenuTree(List<SysMenu> menuList) {
        List<MenuTreeDTO> menuTreeDTOList = menuTreeMapper.toDto(menuList);
        return TreeUtils.getTreeList(menuTreeDTOList);
    }

}
