package com.java.module.sys.service.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 菜单查询参数
 *
 * @author: jcm
 * @date: 2020/09/08
 */
public class MenuListParamsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
        return "MenuListParamsDTO{" +
                "blurry='" + blurry + '\'' +
                ", createTime=" + Arrays.toString(createTime) +
                '}';
    }
}
