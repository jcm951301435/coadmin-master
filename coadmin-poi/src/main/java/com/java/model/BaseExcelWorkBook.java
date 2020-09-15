package com.java.model;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 基础类 包装excel 处理相关信息
 * @author: jcm
 * @date: 2020/09/10
 */
public class BaseExcelWorkBook {

    private Class<?> cls;

    private XSSFWorkbook workbook;

    private String title;

    private int importRowBeginCount;

    private XSSFSheet sheet;

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImportRowBeginCount() {
        return importRowBeginCount;
    }

    public void setImportRowBeginCount(int importRowBeginCount) {
        this.importRowBeginCount = importRowBeginCount;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public BaseExcelWorkBook(Class<?> cls, XSSFWorkbook workbook, String title, int importRowBeginCount, XSSFSheet sheet) {
        this.cls = cls;
        this.workbook = workbook;
        this.title = title;
        this.importRowBeginCount = importRowBeginCount;
        this.sheet = sheet;
    }

    @Override
    public String toString() {
        return "ExcelWorkBookBaseEntity{" +
                "cls=" + cls +
                ", workbook=" + workbook +
                ", title='" + title + '\'' +
                ", importRowBeginCount=" + importRowBeginCount +
                ", sheet=" + sheet +
                '}';
    }
}
