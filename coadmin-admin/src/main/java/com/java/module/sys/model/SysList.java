package com.java.module.sys.model;


import com.java.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snow-flake-id")
    @GenericGenerator(name = "snow-flake-id", strategy = "com.java.config.SnowFlakeIdGenerator")
    @NotNull(groups = Update.class, message = "id 不能为空")
    private Long id;

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

}
