package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * sys_user 用户实体
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Column(name = "username")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 头像名称
     */
    @Column(name = "avatar_name")
    private String avatarName;

    /**
     * 头像路径
     */
    @Column(name = "avatar_path")
    private String avatarPath;

    /**
     * 邮箱
     */
    @NotBlank(message = "email不能为空")
    @Column(name = "email")
    private String email;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 手机号
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 是否启用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * 角色
     */
    @ManyToMany
    @JoinTable(name = "sys_user_role_relation",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<SysRole> roles;

    @ManyToOne
    @JoinColumn(name = "depart_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private SysDepart depart;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUser sysUser = (SysUser) o;
        return this.getId().equals(sysUser.getId()) && username.equals(sysUser.username);
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}