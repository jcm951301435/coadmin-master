package com.java.module.sys.model;

import com.java.model.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 列选项
 *
 * @author: jcm
 * @date: 2020/09/14
 */
public class SysListItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属列选id
     */
    @NotNull(groups = Update.class, message = "id 不能为空")
    @Null(groups = Create.class, message = "id 必须为空")
    private Long listId;

    /**
     * 值
     */
    @NotNull(groups = Create.class, message = "选项键不能为空")
    private String type;

    /**
     * 值
     */
    @NotNull(groups = Create.class, message = "选项值不能为空")
    private String value;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
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
    public String toString() {
        return "SysListItem{" +
                ", listId=" + listId +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
