package com.java.module.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.model.sys.SysUserRoleRelation;
import org.springframework.stereotype.Repository;

/**
 * 用户-角色持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysUserRoleRelationDao extends BaseMapper<SysUserRoleRelation> {

}