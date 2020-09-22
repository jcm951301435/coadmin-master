package com.java.module.sys.service.dto;

import com.java.model.BaseEntity;
import com.java.module.sys.model.SysListItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
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

}
