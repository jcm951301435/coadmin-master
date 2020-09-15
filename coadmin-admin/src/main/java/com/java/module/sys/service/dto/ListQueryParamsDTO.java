package com.java.module.sys.service.dto;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public class ListQueryParamsDTO {

    private String blurry;

    public String getBlurry() {
        return blurry;
    }

    public void setBlurry(String blurry) {
        this.blurry = blurry;
    }

    @Override
    public String toString() {
        return "ListParamsDTO{" +
                "blurry='" + blurry + '\'' +
                '}';
    }
}
