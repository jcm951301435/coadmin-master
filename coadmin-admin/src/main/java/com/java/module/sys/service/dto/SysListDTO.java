package com.java.module.sys.service.dto;

import com.java.model.BaseEntity;
import com.java.module.sys.model.SysListItem;

import java.util.Date;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public class SysListDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 值
     */
    private String value;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 具体选项
     */
    private List<SysListItem> items;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public List<SysListItem> getItems() {
        return items;
    }

    public void setItems(List<SysListItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SysListDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", items=" + items +
                '}';
    }
}
