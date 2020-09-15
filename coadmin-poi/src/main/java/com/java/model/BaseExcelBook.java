package com.java.model;

import java.util.List;

/**
 * ExcelBook 注解映射
 *
 * @author: jcm
 * @date: 2020/09/10
 */
public class BaseExcelBook {

    /**
     * 标题
     */
    private String title;

    /**
     * 导出是否显示序号
     */
    private boolean showIndex;

    /**
     * 导入模板起始行标
     */
    private int importRowBeginCount;

    /**
     * 导入列起始
     */
    private int importColBeginCount;

    private List<BaseExcelColumn> columnList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowIndex() {
        return showIndex;
    }

    public void setShowIndex(boolean showIndex) {
        this.showIndex = showIndex;
    }

    public int getImportRowBeginCount() {
        return importRowBeginCount;
    }

    public void setImportRowBeginCount(int importRowBeginCount) {
        this.importRowBeginCount = importRowBeginCount;
    }

    public int getImportColBeginCount() {
        return importColBeginCount;
    }

    public void setImportColBeginCount(int importColBeginCount) {
        this.importColBeginCount = importColBeginCount;
    }

    public List<BaseExcelColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<BaseExcelColumn> columnList) {
        this.columnList = columnList;
    }

    public BaseExcelBook(String title, boolean showIndex, int importRowBeginCount, int importColBeginCount, List<BaseExcelColumn> columnList) {
        this.title = title;
        this.showIndex = showIndex;
        this.importRowBeginCount = importRowBeginCount;
        this.importColBeginCount = importColBeginCount;
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "ExcelBookEntity{" +
                "title='" + title + '\'' +
                ", showIndex=" + showIndex +
                ", importRowBeginCount=" + importRowBeginCount +
                ", importColBeginCount=" + importColBeginCount +
                ", columnList=" + columnList +
                '}';
    }
}
