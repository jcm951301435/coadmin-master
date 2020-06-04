package com.java.module.sys.model;

import com.java.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_user_avatar 用户头像实体
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserAvatar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String avatarName;

    /**
     * 路径
     */
    private String avatarPath;

}