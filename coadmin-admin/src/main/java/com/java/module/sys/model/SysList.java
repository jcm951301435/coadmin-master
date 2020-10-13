package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * 列选
 *
 * @author: jcm
 * @date: 2020/09/14
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_list")
public class SysList extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @Column(name = "type")
    @NotBlank(groups = Create.class, message = "列选类型不能为空")
    private String type;

    /**
     * 值
     */
    @Column(name = "value")
    @NotBlank(groups = Create.class, message = "列选说明不能为空")
    private String value;

    /**
     * 列选明细
     */
    @OneToMany(mappedBy = "list", fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<SysListItem> sysListItems;

}
