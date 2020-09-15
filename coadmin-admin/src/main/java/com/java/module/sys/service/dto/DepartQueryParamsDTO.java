package com.java.module.sys.service.dto;

import java.util.Arrays;
import java.util.Date;

/**
 * 部门查询条件
 * @author: jcm
 * @date: 2020/09/15
 */
public class DepartQueryParamsDTO {

    /**
     * 模糊搜索
     */
    private String blurry;

    /**
     * 创建时间区间
     */
    private Date[] createTime;

    public String getBlurry() {
        return blurry;
    }

    public void setBlurry(String blurry) {
        this.blurry = blurry;
    }

    public Date[] getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date[] createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DepartQueryParamsDTO{" +
                "blurry='" + blurry + '\'' +
                ", createTime=" + Arrays.toString(createTime) +
                '}';
    }
}
