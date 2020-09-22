package com.java.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author: jcm
 * @date: 2020/05/19
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by", updatable = false)
    private String createBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time", insertable = false)
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_by", insertable = false)
    private String updateBy;

    /* 分组校验 新增 */
    public @interface Create {}

    /* 分组校验 修改 */
    public @interface Update {}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
