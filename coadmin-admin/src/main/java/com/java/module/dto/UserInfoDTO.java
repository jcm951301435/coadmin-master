package com.java.module.dto;

import com.java.module.model.base.BaseEntity;
import com.java.module.model.sys.SysPermission;
import lombok.*;

import java.util.List;

/**
 * 用户信息类
 *
 * @author: jcm
 * @date: 2020/05/28
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private Long avatarId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 权限列表
     */
    private List<SysPermission> permissionList = null;

}
