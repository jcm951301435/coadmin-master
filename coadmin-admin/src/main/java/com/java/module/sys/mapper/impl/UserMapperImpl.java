package com.java.module.sys.mapper.impl;
import com.java.module.sys.mapper.UserMapper;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(SysUser entity) {
        if (entity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(entity.getUsername());
        userDTO.setEmail(entity.getEmail());
        userDTO.setNickName(entity.getNickName());
        userDTO.setMobilePhone(entity.getMobilePhone());
        userDTO.setEnabled(entity.getEnabled());
        userDTO.setCreateTime(entity.getCreateTime());
        userDTO.setCreateBy(entity.getCreateBy());
        userDTO.setUpdateTime(entity.getUpdateTime());
        userDTO.setUpdateBy(entity.getUpdateBy());
        return userDTO;
    }

}
