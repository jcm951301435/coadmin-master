package com.java.module.sys.service;

import com.java.common.model.CommonPage;
import com.java.module.sys.dto.UserDetailsDTO;
import com.java.module.sys.dto.UserInfoDTO;
import com.java.module.sys.model.SysUser;

import java.util.List;


/**
 * 用户业务接口
 *
 * @author: jcm
 * @date: 2020/05/17
 */
public interface SysUserService {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    SysUser getUserByUserName(String username);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int add(SysUser user);

    /**
     * userDetails 转换 UserInfo
     *
     * @param userDetailsDTO
     * @return
     */
    UserInfoDTO userDetailsToUserInfo(UserDetailsDTO userDetailsDTO);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<SysUser> listAll();

    /**
     * 分页查询所有数据
     *
     * @param page
     * @return
     */
    CommonPage<SysUser> listAll(CommonPage<SysUser> page);

}
