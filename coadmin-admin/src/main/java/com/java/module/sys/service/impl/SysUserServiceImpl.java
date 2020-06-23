package com.java.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.common.exception.DuplicateEntityException;
import com.java.common.model.CommonPage;
import com.java.module.sys.dao.SysUserDao;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
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

    public SysUserServiceImpl(SysUserDao userDao) {
        this.userDao = userDao;
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
    @Cacheable(cacheNames = "user", key = "'listAll'")
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
