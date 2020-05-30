package com.java.module.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.common.CommonPage;
import com.java.exception.DuplicateEntityException;
import com.java.module.dao.sys.SysUserDao;
import com.java.module.dto.UserDetailsDTO;
import com.java.module.dto.UserInfoDTO;
import com.java.module.model.sys.SysUser;
import com.java.module.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final PasswordEncoder passwordEncoder;

    public SysUserServiceImpl(SysUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.insert(user);
    }

    @Override
    public UserInfoDTO userDetailsToUserInfo(UserDetailsDTO userDetailsDTO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        SysUser sysUser = userDetailsDTO.getSysUser();
        userInfoDTO.setId(sysUser.getId());
        userInfoDTO.setUsername(sysUser.getUsername());
        userInfoDTO.setEmail(sysUser.getEmail());
        userInfoDTO.setAvatarId(sysUser.getAvatarId());
        userInfoDTO.setNickName(sysUser.getNickName());
        userInfoDTO.setMobilePhone(sysUser.getMobilePhone());
        userInfoDTO.setEnabled(sysUser.getEnabled());
        userInfoDTO.setPermissionList(userDetailsDTO.getPermissionList());
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

}
