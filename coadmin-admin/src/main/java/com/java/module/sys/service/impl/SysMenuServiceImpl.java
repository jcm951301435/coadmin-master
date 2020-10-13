package com.java.module.sys.service.impl;

import com.java.exception.ProjectRunTimeException;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.action.vo.MenuPermissionsVO;
import com.java.module.sys.dao.SysMenuRepository;
import com.java.module.sys.mapper.MenuMapper;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysMenuService;
import com.java.module.sys.dto.query.MenuListQueryDTO;
import com.java.module.sys.service.dto.MenuTreeDTO;
import com.java.util.CollectionUtils;
import com.java.util.SecurityUtils;
import com.java.util.StringUtils;
import com.java.util.TreeUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * 菜单业务接口实现
 *
 * @author: jcm
 * @date: 2020/06/11
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuRepository menuRepository;


    private final MenuMapper menuMapper;

    public SysMenuServiceImpl(SysMenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
//    @Cacheable(value = "menuPermission", key = "#p0.belongAdmin ? #p0.username : #p0.sysUser.id")
    public List<MenuPermissionsVO> buildMenuVO(SecurityUserDetails userDetails) {
        List<SysMenu> menuList = null;
        // admin 获取所有权限 反之获取当前权限
        if (!SecurityUtils.isAdmin(userDetails.getUsername())) {
            SysUser sysUser = userDetails.getSysUser();
            Set<SysRole> roles = sysUser.getRoles();
            if (CollectionUtils.isNotEmpty(roles)) {
                Set<SysMenu> menus = new HashSet<>();
                for (SysRole role : roles) {
                    menus.addAll(role.getMenus());
                }
                menuList = new ArrayList<>(menus);
            }
        } else {
            menuList = menuRepository.findAll();
        }
        List<MenuTreeDTO> menuTreeDTOList = menuMapper.toMenuTree(menuList);
        List<MenuTreeDTO> menuTreeList = TreeUtils.getTreeList(menuTreeDTOList);
        return menuMapper.buildMenuVO(menuTreeList);
    }

    @Override
    public List<MenuTreeDTO> treeList(MenuListQueryDTO params) {
        List<SysMenu> menuList = menuList(params);
        List<MenuTreeDTO> menuTreeDTOList = menuMapper.toMenuTree(menuList);
        return TreeUtils.getTreeList(menuTreeDTOList);
    }

    @Override
    public List<MenuTreeDTO> treeListSort(MenuListQueryDTO params) {
        List<MenuTreeDTO> menuList = treeList(params);
        return TreeUtils.sort(menuList, null);
    }

    /**
     * 获取 sysMenu list
     *
     * @param params .
     * @return .
     */
    private List<SysMenu> menuList(MenuListQueryDTO params) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "type"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "sort"));
        return menuRepository.findAll((Specification<SysMenu>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (params != null) {
                Date[] createTimeArray = params.getCreateTime();
                String blurry = params.getBlurry();
                if (createTimeArray != null && createTimeArray.length > 0) {
                    Date createTimeBegin = createTimeArray[0];
                    Date createTimeEnd = null;
                    if (createTimeArray.length > 1) {
                        createTimeEnd = createTimeArray[1];
                    }
                    if (createTimeBegin != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"),
                                createTimeBegin));
                    }
                    if (createTimeEnd != null) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"),
                                createTimeEnd));
                    }
                }
                if (StringUtils.isNotBlank(blurry)) {
                    blurry = "%" + blurry + "%";
                    List<Predicate> blurryPredicates = new ArrayList<>();
                    blurryPredicates.add(criteriaBuilder.like(root.get("title"), blurry));
                    blurryPredicates.add(criteriaBuilder.like(root.get("permission"), blurry));
                    blurryPredicates.add(criteriaBuilder.like(root.get("componentUrl"), blurry));
                    predicates.add(criteriaBuilder.or(blurryPredicates.toArray(new Predicate[0])));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(orders));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menuPermission", key = "'admin'", condition = "#result > 0")
    public int create(SysMenu sysMenu) {
        checkDuplicateBeforeSave(sysMenu, false);
        menuRepository.save(sysMenu);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menuPermission", key = "'admin'", condition = "#result > 0")
    public int update(SysMenu sysMenu) {
        if (sysMenu.getId().equals(sysMenu.getPid())) {
            throw new ProjectRunTimeException("上级目录不能为自己！");
        }
        checkDuplicateBeforeSave(sysMenu, true);
        menuRepository.save(sysMenu);
        return 0;
    }

    /**
     * 保存前检查重复
     *
     * @param sysMenu  .
     * @param isUpdate .
     */
    private void checkDuplicateBeforeSave(SysMenu sysMenu, boolean isUpdate) {
//        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
//        String title = sysMenu.getTitle();
//        String name = sysMenu.getName();
//        Long id = sysMenu.getId();
//        Long pid = sysMenu.getPid();
//        if (StringUtils.isNotEmpty(title)) {
//            wrapper.eq("title", title);
//            wrapper.eq("pid", pid);
//            if (isUpdate) {
//                wrapper.ne("id", id);
//            }
//            int count = menuDao.selectCount(wrapper);
//            if (count > 0) {
//                throw new DuplicateEntityException(String.format("此标题已存在: %s", title));
//            }
//        }
//        if (StringUtils.isNotEmpty(name)) {
//            wrapper.clear();
//            wrapper.eq("name", name);
//            if (isUpdate) {
//                wrapper.ne("id", id);
//            }
//            int count = menuDao.selectCount(wrapper);
//            if (count > 0) {
//                throw new DuplicateEntityException(String.format("此组件名称已存在: %s", name));
//            }
//        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menuPermission", key = "'admin'", condition = "#result > 0")
    public int delete(List<Long> ids) {
        return menuRepository.deleteByIdIn(ids);
    }

}
