package com.java.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.common.exception.DuplicateEntityException;
import com.java.common.model.CommonPage;
import com.java.common.util.CollectionUtils;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.dao.SysMenuDao;
import com.java.module.sys.dao.SysRoleDao;
import com.java.module.sys.dao.SysUserDao;
import com.java.module.sys.model.SysMenu;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import com.java.module.sys.service.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务接口实现类
 *
 * @author: jcm
 * @date: 2020/05/17
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao userDao;

    private final SysRoleDao roleDao;

    private final SysMenuDao menuDao;

    public SysUserServiceImpl(SysUserDao userDao, SysRoleDao roleDao, SysMenuDao menuDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.menuDao = menuDao;
    }

    @Override
    public SysUser getUserByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userDao.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(SysUser user) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        String username = user.getUsername();
        wrapper.eq("username", username);
        Integer existsCount = userDao.selectCount(wrapper);
        if (existsCount != null && existsCount > 0) {
            throw new DuplicateEntityException("用户名已存在：" + username);
        }
        return userDao.insert(user);
    }

    @Override
    public UserInfoDTO userDetailsToUserInfo(SecurityUserDetails securityUserDetails) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        SysUser sysUser = securityUserDetails.getSysUser();
        userInfoDTO.setId(sysUser.getId());
        userInfoDTO.setUsername(sysUser.getUsername());
        userInfoDTO.setEmail(sysUser.getEmail());
        userInfoDTO.setAvatarId(sysUser.getAvatarId());
        userInfoDTO.setNickName(sysUser.getNickName());
        userInfoDTO.setMobilePhone(sysUser.getMobilePhone());
        userInfoDTO.setEnabled(sysUser.getEnabled());
        return userInfoDTO;
    }

    @Override
    public List<SysUser> listAll() {
        return userDao.selectList(null);
    }

    @Override
    public CommonPage<SysUser> listAll(CommonPage<SysUser> commonPage) {
        Page<SysUser> page = commonPage.getPage();
        userDao.selectPage(page, null);
        return CommonPage.fromPage(page);
    }

    @Override
    public List<SysMenu> listMenusByUserId(Long userId) {
        List<SysRole> roleList = roleDao.listByUserId(userId);
        if (CollectionUtils.isEmpty(roleList)) {
            return new ArrayList<>();
        }
        Long[] roles = roleList.stream().map(SysRole::getId).toArray(Long[]::new);
        return menuDao.listByRoleIds(roles);
    }

}
