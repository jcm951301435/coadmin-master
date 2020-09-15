package com.java.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel 样式处理
 * @author: jcm
 * @date: 2020/09/10
 */
public class StyleUtils {

    private static final String TITLE_FONT_NAME = "华文中宋";

    private static final String COLUMN_TITLE_FONT_NAME = "黑体";

    private static final String COLUMN_VALUE_FONT_NAME = "宋体";

    /**
     * 默认样式
     * @param wb .
     * @return .
     */
    public static XSSFCellStyle getDefaultStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        return style;
    }

    /**
     * 标题样式
     * @param wb .
     * @return .
     */
    public static XSSFCellStyle getTitleStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = getDefaultStyle(wb);
        XSSFFont font = wb.createFont();
        font.setFontName(TITLE_FONT_NAME);
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 字段名样式
     * @param wb .
     * @return .
     */
    public static XSSFCellStyle getColumnTitleStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = getDefaultStyle(wb);
        XSSFFont font = wb.createFont();
        font.setFontName(COLUMN_TITLE_FONT_NAME);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }

    /**
     * 字段值样式
     * @param wb .
     * @return .
     */
    public static XSSFCellStyle getColumnStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = getDefaultStyle(wb);
        XSSFFont font = wb.createFont();
        font.setFontName(COLUMN_VALUE_FONT_NAME);
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

}
