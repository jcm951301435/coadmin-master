package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 列选项
 *
 * @author: jcm
 * @date: 2020/09/14
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_list_item")
public class SysListItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 值
     */
    @Column(name = "type")
    @NotNull(groups = Create.class, message = "选项键不能为空")
    private String type;

    /**
     * 值
     */
    @Column(name = "value")
    @NotNull(groups = Create.class, message = "选项值不能为空")
    private String value;

    /**
     * 列选项
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "list_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private SysList list;

}
