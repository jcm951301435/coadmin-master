package com.java.module.sys.service.dto;

import com.java.annotation.ExcelBook;
import com.java.annotation.ExcelColumn;
import com.java.model.BaseTreeNode;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
@ExcelBook(title = "部门列表")
public class DepartTreeDTO extends BaseTreeNode<DepartTreeDTO> {

    /**
     * 部门名称
     */
    @ExcelColumn(text = "部门名称", sort = 1)
    private String name;

    /**
     * 排序
     */
    @ExcelColumn(text = "部门排序", sort = 1)
    private Long sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "DepartTreeDTO{" +
                "name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }
}
