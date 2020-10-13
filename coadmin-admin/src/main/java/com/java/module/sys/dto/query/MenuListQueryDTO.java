package com.java.module.sys.dto.query;

import lombok.Data;

import java.util.Date;

/**
 * 菜单查询参数
 *
 * @author: jcm
 * @date: 2020/09/08
 */
@Data
public class MenuListQueryDTO {

    /**
     * 模糊搜索
     */
    private String blurry;

    /**
     * 创建时间区间
     */
    private Date[] createTime;

    /**
     * 不包含按钮
     */
    private Boolean noButton;

}
