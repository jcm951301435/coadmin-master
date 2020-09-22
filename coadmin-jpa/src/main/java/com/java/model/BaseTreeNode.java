package com.java.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseTreeNode<T extends BaseTreeNode<T>> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(hidden = true)
    private Long pid;

    private List<T> children;

}
