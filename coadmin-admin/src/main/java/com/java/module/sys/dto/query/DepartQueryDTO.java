package com.java.module.sys.dto.query;

import lombok.Data;

import java.util.Date;

/**
 * 部门查询条件
 * @author: jcm
 * @date: 2020/09/15
 */
@Data
public class DepartQueryDTO {

    /**
     * 模糊搜索
     */
    private String blurry;

    /**
     * 创建时间区间
     */
    private Date[] createTime;

}
