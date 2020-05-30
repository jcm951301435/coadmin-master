package com.java.module.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.model.sys.SysUser;
import org.springframework.stereotype.Repository;

/**
 * 用户持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

}