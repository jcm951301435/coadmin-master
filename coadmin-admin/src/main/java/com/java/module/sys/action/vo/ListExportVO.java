package com.java.module.sys.action.vo;

import com.java.annotation.ExcelBook;
import com.java.annotation.ExcelColumn;

/**
 * list 导出
 * @author: jcm
 * @date: 2020/09/15
 */
@ExcelBook(title = "列选列表")
public class ListExportVO {

    private Long id;

    /**
     * 列选类型
     */
    @ExcelColumn(text = "列选类型", sort = 1)
    private String type;

    /**
     * 列选说明
     */
    @ExcelColumn(text = "列选说明", sort = 2)
    private String value;

    /**
     * 项 id
     */
    private Long itemId;

    /**
     * 项 键
     */
    @ExcelColumn(text = "选项键", sort = 3)
    private String itemType;

    /**
     * 项 值
     */
    @ExcelColumn(text = "选项值", sort = 3)
    private String itemValue;

    public Long getId() {
        return id;
    }

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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    @Override
    public String toString() {
        return "ListExportVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", itemValue='" + itemValue + '\'' +
                '}';
    }
}
