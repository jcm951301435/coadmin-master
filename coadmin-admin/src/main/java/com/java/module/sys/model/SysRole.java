package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * sys_role 角色
 *
 * @author: jcm
 * @date: 2020/05/08
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snow-flake-id")
    @GenericGenerator(name = "snow-flake-id", strategy = "com.java.config.SnowFlakeIdGenerator")
    private Long id;

    /**
     * 角色名
     */
    @Column(name = "name")
    private String name;

    /**
     * 数据权限类型 0:全部 1:仅本级 2:自定义
     */
    @Column(name = "scope_type")
    private Integer scopeType;

    /**
     * 级别
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @ManyToMany(mappedBy = "roles")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<SysUser> users;

    @ManyToMany
    @JoinTable(name = "sys_role_menu_relation",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    private Set<SysMenu> menus;

    @ManyToMany
    @JoinTable(name = "sys_role_depart_relation",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "depart_id", referencedColumnName = "id")})
    private Set<SysDepart> departs;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysRole sysRole = (SysRole) o;
        return id.equals(sysRole.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}