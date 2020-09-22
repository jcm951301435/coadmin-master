package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 部门
 *
 * @author: jcm
 * @date: 2020/09/14
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_depart")
public class SysDepart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snow-flake-id")
    @GenericGenerator(name = "snow-flake-id", strategy = "com.java.config.SnowFlakeIdGenerator")
    @NotNull(groups = Update.class, message = "id 不能为空")
    private Long id;

    /**
     * 部门名称
     */
    @Column(name = "name")
    @NotBlank(groups = Create.class, message = "菜单标题不能为空")
    private String name;

    /**
     * 上级主键
     */
    @Column(name = "pid")
    @NotNull(groups = Update.class, message = "上级部门不能为空")
    private Long pid;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Long sort;

    @OneToMany(mappedBy = "depart", fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<SysUser> users;

    @ManyToMany(mappedBy = "departs")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<SysRole> roles;

}