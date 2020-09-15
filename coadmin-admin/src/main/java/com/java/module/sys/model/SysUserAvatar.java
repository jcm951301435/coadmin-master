package com.java.module.sys.model;


import com.java.model.BaseEntity;

/**
 * sys_user_avatar 用户头像实体
 *
 * @author: jcm
 * @date: 2020/05/08
 */
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

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}