package com.java.module.sys.service.dto;

import com.java.annotation.ExcelBook;
import com.java.annotation.ExcelColumn;
import com.java.model.BaseTreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelBook(title = "部门列表")
public class DepartTreeDTO extends BaseTreeNode<DepartTreeDTO> {

    private static final long serialVersionUID = 1L;

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

}
