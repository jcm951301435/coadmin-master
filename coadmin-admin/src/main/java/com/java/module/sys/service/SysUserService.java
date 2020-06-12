package com.java.module.sys.service;

import com.java.common.model.CommonPage;
import com.java.module.security.model.SecurityUserDetails;
import com.java.module.sys.service.dto.UserInfoDTO;
import com.java.module.sys.model.SysMenu;
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
     * @param securityUserDetails
     * @return
     */
    UserInfoDTO userDetailsToUserInfo(SecurityUserDetails securityUserDetails);

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

    /**
     * 根据用户编号查询按钮列表
     *
     * @param userId
     * @return
     */
    List<SysMenu> listMenusByUserId(Long userId);

}
