package com.java.module.sys.mapper.impl;

import com.java.module.sys.action.vo.MenuMetaVO;
import com.java.module.sys.action.vo.MenuPermissionsVO;
import com.java.module.sys.mapper.MenuMapper;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.service.SysListItemService;
import com.java.module.sys.service.SysListService;
import com.java.module.sys.service.dto.MenuTreeDTO;
import com.java.module.sys.service.dto.SysListDTO;
import com.java.util.CollectionUtils;
import com.java.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/11
 */
@Component
public class MenuMapperImpl implements MenuMapper, InitializingBean {

    private List<SysListItem> menuTypeList;

    private final SysListService listService;

    private final SysListItemService itemService;

    public MenuMapperImpl(SysListService listService, SysListItemService itemService) {
        this.listService = listService;
        this.itemService = itemService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SysListDTO menuType = listService.findOneByType("menu_type");
        menuTypeList = menuType.getItems();
    }

    @Override
    public MenuTreeDTO toMenuTree(SysMenu entity) {
        if (entity == null) {
            return null;
        }
        MenuTreeDTO menuTreeDTO = new MenuTreeDTO();
        menuTreeDTO.setId(entity.getId());
        menuTreeDTO.setCreateTime(entity.getCreateTime());
        menuTreeDTO.setCreateBy(entity.getCreateBy());
        menuTreeDTO.setUpdateTime(entity.getUpdateTime());
        menuTreeDTO.setUpdateBy(entity.getUpdateBy());
        menuTreeDTO.setPid(entity.getPid());
        menuTreeDTO.setName(entity.getName());
        menuTreeDTO.setType(entity.getType());
        menuTreeDTO.setTypeStr(itemService.getValueByType(String.valueOf(entity.getType()), menuTypeList));
        menuTreeDTO.setTitle(entity.getTitle());
        menuTreeDTO.setPath(entity.getPath());
        menuTreeDTO.setPermission(entity.getPermission());
        menuTreeDTO.setComponentUrl(entity.getComponentUrl());
        menuTreeDTO.setIcon(entity.getIcon());
        menuTreeDTO.setHidden(entity.getHidden());
        menuTreeDTO.setCanSee(entity.getHidden() ? "不可见" : "可见");
        menuTreeDTO.setSort(entity.getSort());
        return menuTreeDTO;
    }

    @Override
    public List<MenuTreeDTO> toMenuTree(List<SysMenu> entityList) {
        if (entityList == null) {
            return null;
        }
        List<MenuTreeDTO> list = new ArrayList<>(entityList.size());
        for (SysMenu sysMenu : entityList) {
            list.add(toMenuTree(sysMenu));
        }
        return list;
    }

    @Override
    public List<MenuPermissionsVO> buildMenuVO(List<MenuTreeDTO> menuTreeDTOList) {
        List<MenuPermissionsVO> menuPermissionsVOList = new ArrayList<>();
        MenuPermissionsVO menuPermissionsVO;
        MenuMetaVO menuMetaVO;
        for (MenuTreeDTO treeDTO : menuTreeDTOList) {
            menuPermissionsVO = new MenuPermissionsVO();
            menuPermissionsVO.setName(treeDTO.getName());
            String path = treeDTO.getPath();
            Integer type = treeDTO.getType();
            String componentUrl = treeDTO.getComponentUrl();
            if (type == 0) {
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
                menuPermissionsVO.setComponent(StringUtils.isNotBlank(componentUrl) ? componentUrl : "Layout");
                menuPermissionsVO.setRedirect("noRedirect");
            } else {
                if (StringUtils.isNotBlank(componentUrl)) {
                    menuPermissionsVO.setComponent(componentUrl);
                }
            }
            menuPermissionsVO.setPath(path);
            menuPermissionsVO.setHidden(treeDTO.getHidden());
            menuPermissionsVO.setType(type);
            menuMetaVO = new MenuMetaVO();
            menuMetaVO.setIcon(treeDTO.getIcon());
            menuMetaVO.setTitle(treeDTO.getTitle());
            menuPermissionsVO.setMeta(menuMetaVO);
            if (CollectionUtils.isNotEmpty(treeDTO.getChildren())) {
                menuPermissionsVO.setChildren(buildMenuVO(treeDTO.getChildren()));
            }
            menuPermissionsVOList.add(menuPermissionsVO);
        }
        return menuPermissionsVOList;
    }


}
