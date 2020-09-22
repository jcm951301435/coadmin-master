package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snow-flake-id")
    @GenericGenerator(name = "snow-flake-id", strategy = "com.java.config.SnowFlakeIdGenerator")
    @NotNull(groups = Update.class, message = "id 不能为空")
    private Long id;

    /**
     * 所属列选id
     */
    @Column(name = "list_id")
    private Long listId;

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

}
