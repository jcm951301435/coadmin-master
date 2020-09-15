package com.java.module.sys.mapper;


import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.dto.UserDTO;

/**
 * UserInfo <=> SysUser
 *
 * @author: jcm
 * @date: 2020/06/12
 */
public interface UserMapper{

    /**
     * SysUser -> UserDTO
     * @param entity .
     * @return .
     */
    UserDTO toUserDTO(SysUser entity);

}
