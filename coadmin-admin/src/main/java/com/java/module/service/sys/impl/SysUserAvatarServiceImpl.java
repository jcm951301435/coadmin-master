package com.java.module.service.sys.impl;

import com.java.module.dao.sys.SysUserAvatarDao;
import com.java.module.service.sys.SysUserAvatarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户头像业务处理实现
 *
 * @author: jcm
 * @date: 2020/05/17
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysUserAvatarServiceImpl implements SysUserAvatarService {

    private final SysUserAvatarDao userAvatarDao;

    public SysUserAvatarServiceImpl(SysUserAvatarDao userAvatarDao) {
        this.userAvatarDao = userAvatarDao;
    }

}
