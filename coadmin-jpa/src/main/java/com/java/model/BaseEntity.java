package com.java.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author: jcm
 * @date: 2020/05/19
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snow-flake-id")
    @NotNull(groups = Update.class, message = "id 不能为空")
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by", updatable = false)
    @CreatedBy
    private String createBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time", insertable = false)
    @LastModifiedDate
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_by", insertable = false)
    @LastModifiedBy
    private String updateBy;

    /* 分组校验 新增 */
    public @interface Create {}

    /* 分组校验 修改 */
    public @interface Update {}

}
