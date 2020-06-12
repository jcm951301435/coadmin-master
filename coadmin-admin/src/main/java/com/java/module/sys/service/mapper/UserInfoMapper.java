package com.java.module.sys.service.mapper;

import com.java.common.model.BaseMapper;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.dto.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * UserInfo <=> SysUser
 *
 * @author: jcm
 * @date: 2020/06/12
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper extends BaseMapper<UserInfoDTO, SysUser> {
}
