package com.java.module.sys.service.impl;

import com.java.module.sys.dao.SysRoleDao;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务接口实现
 *
 * @author: jcm
 * @date: 2020/06/14
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleDao roleDao;

    public SysRoleServiceImpl(SysRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<SysRole> listByUserId(Long userId) {
        return roleDao.listByUserId(userId);
    }

}
