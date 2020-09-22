package com.java.module.sys.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * 部门查询条件
 * @author: jcm
 * @date: 2020/09/15
 */
@Data
public class DepartQueryParamsDTO {

    /**
     * 模糊搜索
     */
    private String blurry;

    /**
     * 创建时间区间
     */
    private Date[] createTime;

}
