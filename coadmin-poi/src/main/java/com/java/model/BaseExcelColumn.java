package com.java.model;

import com.java.enumerate.ExcelNumFormatEnum;

import java.lang.reflect.Field;

/**
 * ExcelColumn 注解映射
 *
 * @author: jcm
 * @date: 2020/09/10
 */
public class BaseExcelColumn {

    /**
     * 字段名
     */
    private String text;

    /**
     * 宽度
     */
    private int colWidth;

    /**
     * 字体颜色
     */
    private short color;

    /**
     * 列号
     */
    private int sort;

    /**
     * 数字格式化
     */
    private ExcelNumFormatEnum numFormat;

    private String fieldName;

    private String methodName;

    private Field field;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
    }

    public short getColor() {
        return color;
    }

    public void setColor(short color) {
        this.color = color;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public ExcelNumFormatEnum getNumFormat() {
        return numFormat;
    }

    public void setNumFormat(ExcelNumFormatEnum numFormat) {
        this.numFormat = numFormat;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public BaseExcelColumn(String text, int colWidth, short color, int sort, ExcelNumFormatEnum numFormat,
                           String fieldName, String methodName, Field field) {
        this.text = text;
        this.colWidth = colWidth;
        this.color = color;
        this.sort = sort;
        this.numFormat = numFormat;
        this.fieldName = fieldName;
        this.methodName = methodName;
        this.field = field;
    }

    @Override
    public String toString() {
        return "BaseExcelColumn{" +
                "text='" + text + '\'' +
                ", colWidth=" + colWidth +
                ", color=" + color +
                ", sort=" + sort +
                ", numFormat=" + numFormat +
                ", fieldName='" + fieldName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", field=" + field +
                '}';
    }
}
