package com.java.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.exception.DuplicateEntityException;
import com.java.exception.ProjectRunTimeException;
import com.java.model.BaseEntity;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.action.vo.MenuPermissionsVO;
import com.java.module.sys.dao.SysMenuDao;
import com.java.module.sys.dao.SysRoleDao;
import com.java.module.sys.mapper.MenuMapper;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.service.SysMenuService;
import com.java.module.sys.service.dto.MenuListParamsDTO;
import com.java.module.sys.service.dto.MenuTreeDTO;
import com.java.util.CollectionUtils;
import com.java.util.SecurityUtils;
import com.java.util.StringUtils;
import com.java.util.TreeUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单业务接口实现
 *
 * @author: jcm
 * @date: 2020/06/11
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuDao menuDao;

    private final SysRoleDao roleDao;

    private final MenuMapper menuMapper;

    public SysMenuServiceImpl(SysMenuDao menuDao, SysRoleDao roleDao, MenuMapper menuMapper) {
        this.menuDao = menuDao;
        this.roleDao = roleDao;
        this.menuMapper = menuMapper;
    }

    @Override
    @Cacheable(value = "menuPermission", key = "#p0.belongAdmin ? #p0.username : #p0.sysUser.id")
    public List<MenuPermissionsVO> buildMenuVO(SecurityUserDetails userDetails) {
        List<MenuTreeDTO> menuTreeList = listByUser(userDetails);
        return menuMapper.buildMenuVO(menuTreeList);
    }

    @Override
    public List<MenuTreeDTO> listByUser(SecurityUserDetails userDetails) {
        Long[] roleIds = null;
        if (!SecurityUtils.isAdmin(userDetails.getUsername())) {
            List<SysRole> roleList = roleDao.listByUserId(userDetails.getSysUser().getId());
            if (CollectionUtils.isEmpty(roleList)) {
                return new ArrayList<>();
            }
            roleIds = roleList.stream().map(BaseEntity::getId).toArray(Long[]::new);
        }
        List<SysMenu> menuList = listByRoleIds(roleIds);
        List<MenuTreeDTO> menuTreeDTOList = menuMapper.toMenuTree(menuList);
        return TreeUtils.getTreeList(menuTreeDTOList);
    }

    @Override
    public List<SysMenu> listByRoleIds(Long[] roleIds) {
        return menuDao.listByRoleIds(roleIds);
    }

    @Override
    public List<MenuTreeDTO> treeList(MenuListParamsDTO params) {
        List<SysMenu> menuList = menuList(params);
        List<MenuTreeDTO> menuTreeDTOList = menuMapper.toMenuTree(menuList);
        return TreeUtils.getTreeList(menuTreeDTOList);
    }

    @Override
    public List<MenuTreeDTO> treeListSort(MenuListParamsDTO params) {
        List<MenuTreeDTO> menuList = treeList(params);
        return TreeUtils.sort(menuList, null);
    }

    /**
     * 获取 sysMenu list
     * @param params .
     * @return .
     */
    private List<SysMenu> menuList(MenuListParamsDTO params) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        Date[] createTimeArray = params.getCreateTime();
        if (createTimeArray != null && createTimeArray.length > 0) {
            Date createTimeBegin = createTimeArray[0];
            Date createTimeEnd = null;
            if (createTimeArray.length > 1) {
                createTimeEnd = createTimeArray[1];
            }
            if (createTimeBegin != null) {
                wrapper.ge("create_time", createTimeBegin);
            }
            if (createTimeEnd != null) {
                wrapper.le("create_time", createTimeEnd);
            }
        }
        String blurry = params.getBlurry();
        if (StringUtils.isNotBlank(blurry)) {
            wrapper.and(w -> w.like("title", blurry)
                    .or().like("permission", blurry)
                    .or().like("component_url", blurry));
        }
        wrapper.orderByAsc("type").orderByAsc("sort");
        return menuDao.selectList(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menuPermission", key = "'admin'", condition = "#result > 0")
    public int create(SysMenu sysMenu) {
        checkDuplicateBeforeSave(sysMenu, false);
        return menuDao.insert(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menuPermission", key = "'admin'", condition = "#result > 0")
    public int update(SysMenu sysMenu) {
        if (sysMenu.getId().equals(sysMenu.getPid())) {
            throw new ProjectRunTimeException("上级目录不能为自己！");
        }
        checkDuplicateBeforeSave(sysMenu, true);
        return menuDao.updateById(sysMenu);
    }

    /**
     * 保存前检查重复
     *
     * @param sysMenu .
     */
    private void checkDuplicateBeforeSave(SysMenu sysMenu, boolean isUpdate) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        String title = sysMenu.getTitle();
        String name = sysMenu.getName();
        Long id = sysMenu.getId();
        Long pid = sysMenu.getPid();
        if (StringUtils.isNotEmpty(title)) {
            wrapper.eq("title", title);
            wrapper.eq("pid", pid);
            if (isUpdate) {
                wrapper.ne("id", id);
            }
            int count = menuDao.selectCount(wrapper);
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此标题已存在: %s", title));
            }
        }
        if (StringUtils.isNotEmpty(name)) {
            wrapper.clear();
            wrapper.eq("name", name);
            if (isUpdate) {
                wrapper.ne("id", id);
            }
            int count = menuDao.selectCount(wrapper);
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此组件名称已存在: %s", name));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menuPermission", key = "'admin'", condition = "#result > 0")
    public int delete(List<Long> ids) {
        return menuDao.deleteBatchIds(ids);
    }

}
