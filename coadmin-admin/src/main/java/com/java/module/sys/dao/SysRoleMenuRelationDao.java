package com.java.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.sys.model.SysRoleMenuRelation;
import org.springframework.stereotype.Repository;

/**
 * 角色-权限持久化接口
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Repository
public interface SysRoleMenuRelationDao extends BaseMapper<SysRoleMenuRelation> {

}