package com.java.module.sys.service.dto;

import com.java.common.model.BaseTreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTreeDTO extends BaseTreeNode<MenuTreeDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 权限值
     */
    private String value;

    /**
     * 路径
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 排序
     */
    private Long sort;


}
