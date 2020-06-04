package com.java.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.sys.model.SysUserAvatar;
import org.springframework.stereotype.Repository;

/**
 * 用户头像持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysUserAvatarDao extends BaseMapper<SysUserAvatar> {

}