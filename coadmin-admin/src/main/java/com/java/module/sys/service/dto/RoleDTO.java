package com.java.module.sys.service.dto;

import com.java.model.BaseEntity;
import com.java.module.sys.model.SysDepart;
import com.java.module.sys.model.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(groups = BaseEntity.Update.class, message = "id 不能为空")
    private Long id;

    /**
     * 角色名
     */
    @NotBlank(groups = Create.class, message = "角色名称不能为空")
    private String name;

    /**
     * 数据权限类型 0:全部 1:仅本级 2:自定义
     */
    @NotNull(groups = Create.class, message = "数据权限不能为空")
    private Integer scopeType;

    /**
     * 数据权限
     */
    private List<SysDepart> departs;

    /**
     * 菜单权限
     */
    private List<SysMenu> menus;

    /**
     * 备注
     */
    private String remark;

}
