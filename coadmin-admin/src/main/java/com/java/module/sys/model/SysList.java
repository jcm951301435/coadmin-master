package com.java.module.sys.model;

import com.java.model.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 列选
 *
 * @author: jcm
 * @date: 2020/09/14
 */
public class SysList extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = Update.class, message = "id 不能为空")
    @Null(groups = Create.class, message = "id 必须为空")
    private Long id;

    /**
     * 类型
     */
    @NotBlank(groups = Create.class, message = "列选类型不能为空")
    private String type;

    /**
     * 值
     */
    @NotBlank(groups = Create.class, message = "列选说明不能为空")
    private String value;

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
    public String toString() {
        return "SysList{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
