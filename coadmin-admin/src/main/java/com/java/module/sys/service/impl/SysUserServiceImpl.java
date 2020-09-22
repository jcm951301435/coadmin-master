package com.java.module.sys.service.impl;

import com.java.exception.DuplicateEntityException;
import com.java.module.sys.dao.SysUserRepository;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务接口实现类
 *
 * @author: jcm
 * @date: 2020/05/17
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

    private final SysUserRepository userRepository;

    public SysUserServiceImpl(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SysUser getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser add(SysUser user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if (userRepository.findByUsername(username) != null) {
            throw new DuplicateEntityException("用户名已存在：" + username);
        }
        if (userRepository.findByEmail(email) != null) {
            throw new DuplicateEntityException("email已存在：" + email);
        }
        return userRepository.save(user);
    }

}
