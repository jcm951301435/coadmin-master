package com.java.module.sys.dao;

import com.java.module.sys.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    /**
     * 根据用户名查询用户
     *
     * @param username .
     * @return .
     */
    SysUser findByUsername(String username);

    /**
     * 根据email查询用户
     * @param email .
     * @return .
     */
    SysUser findByEmail(String email);

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

}