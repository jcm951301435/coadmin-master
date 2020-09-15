package com.java.util;

import com.java.enumerate.ExcelNumFormatEnum;
import com.java.model.BaseExcelBook;
import com.java.model.BaseExcelColumn;
import com.java.model.BaseExcelWorkBook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/10
 */
public class ExcelUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    private static final short TITLE_ROW_HEIGHT = 34 * 20;

    private static final short COLUMN_TITLE_ROW_HEIGHT = 34 * 20;

    private static final int TITLE_COUNT = 2;

    private static final String XLS_FILE_SUFFIX_2003 = "xls";

    private static final String XLS_FILE_SUFFIX_2007 = "xlsx";

    /**
     * 下载 excel
     *
     * @param list     数据源
     * @param cls      bean类
     * @param response 响应类
     * @param <T>      bean类型
     */
    public static <T> void downLoad(List<T> list, Class<T> cls, HttpServletResponse response) {
        BaseExcelBook baseExcelBook = AnnotationUtils.getExcelBook(cls);
        String title = baseExcelBook.getTitle();
        title = title + "." + XLS_FILE_SUFFIX_2007;
        XSSFWorkbook wb = generateExcelBook(list, cls);
        response.setHeader("Content-type", "application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + URLEncoder.encode(title, "UTF-8"));
            wb.write(response.getOutputStream());
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造 XSSFWorkbook
     *
     * @param list .
     * @param cls  .
     * @param <T>  .
     * @return .
     */
    public static <T> XSSFWorkbook generateExcelBook(List<T> list, Class<T> cls) {
        XSSFWorkbook wb = new XSSFWorkbook();
        BaseExcelWorkBook baseExcelWorkBook = getExcelWorkBookBaseEntity(wb, cls);
        initTitle(baseExcelWorkBook);
        initColumnTitle(baseExcelWorkBook);
        initColumnValue(baseExcelWorkBook, list);
        return wb;
    }

    /**
     * 构造 基础信息包装类
     *
     * @param wb  .
     * @param cls .
     * @return .
     */
    private static BaseExcelWorkBook getExcelWorkBookBaseEntity(XSSFWorkbook wb, Class<?> cls) {
        BaseExcelBook excelBook = AnnotationUtils.getExcelBook(cls);
        String title = excelBook.getTitle();
        int importRowBeginCount = excelBook.getImportRowBeginCount();
        XSSFSheet sheet = wb.createSheet(title);
        return new BaseExcelWorkBook(cls, wb, title, importRowBeginCount, sheet);
    }

    /**
     * 生成标题
     *
     * @param excelWorkBook .
     */
    private static void initTitle(BaseExcelWorkBook excelWorkBook) {
        LOGGER.info("设置表格头内容开始");
        Class<?> cls = excelWorkBook.getCls();
        BaseExcelBook baseExcelBook = AnnotationUtils.getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        XSSFSheet sheet = excelWorkBook.getSheet();
        Row titleRow = sheet.createRow(0);
        titleRow.setHeight(TITLE_ROW_HEIGHT);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(StyleUtils.getTitleStyle(excelWorkBook.getWorkbook()));
        titleCell.setCellValue(excelWorkBook.getTitle());
        if (columnList.size() > 1) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnList.size() - 1));
        }
        LOGGER.info("设置表格头内容结束");
    }

    /**
     * 生成列字段名
     *
     * @param excelWorkBook .
     */
    private static void initColumnTitle(BaseExcelWorkBook excelWorkBook) {
        LOGGER.info("设置表格字段头内容开始");
        Class<?> cls = excelWorkBook.getCls();
        BaseExcelBook baseExcelBook = AnnotationUtils.getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        XSSFSheet sheet = excelWorkBook.getSheet();
        Row colTextRow = sheet.createRow(1);
        colTextRow.setHeight(COLUMN_TITLE_ROW_HEIGHT);
        for (int i = 0; i < columnList.size(); i++) {
            BaseExcelColumn baseExcelColumn = columnList.get(i);
            sheet.setColumnWidth(i, baseExcelColumn.getColWidth());
            Cell colTextCell = colTextRow.createCell(i);
            colTextCell.setCellStyle(StyleUtils.getColumnTitleStyle(excelWorkBook.getWorkbook()));
            colTextCell.setCellValue(baseExcelColumn.getText());
        }
        LOGGER.info("设置表格字段头内容结束");
    }

    /**
     * 生成列值
     *
     * @param excelWorkBook .
     */
    private static <T> void initColumnValue(BaseExcelWorkBook excelWorkBook, List<T> list) {
        LOGGER.info("设置表格字段值开始");
        Class<?> cls = excelWorkBook.getCls();
        BaseExcelBook baseExcelBook = AnnotationUtils.getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        XSSFSheet sheet = excelWorkBook.getSheet();
        LOGGER.info("循环设置值开始");
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Row rowTemp = sheet.createRow(i + TITLE_COUNT);
            for (int j = 0; j < columnList.size(); j++) {
                BaseExcelColumn column = columnList.get(j);
                String fieldName = column.getFieldName();
                Cell cellTemp = rowTemp.createCell(j);
                CellStyle cellStyle = StyleUtils.getColumnStyle(excelWorkBook.getWorkbook());
                cellTemp.setCellStyle(cellStyle);
                String value;
                if (AnnotationUtils.checkIsIndexFiled(fieldName)) {
                    value = i + 1 + "";
                } else {
                    value = getFormatValue(obj, column);
                }
                cellTemp.setCellValue(value);
            }
        }
        LOGGER.info("循环设置值结束");
        LOGGER.info("设置表格字段值结束");
    }

    /**
     * 获取格式化后的属性值
     *
     * @param object .
     * @param column .
     * @return .
     */
    private static String getFormatValue(Object object, BaseExcelColumn column) {
        String methodName = column.getMethodName();
        Object value = getValue(object, methodName);
        if (value == null) {
            return null;
        }
        String valueStr = value.toString();
        ExcelNumFormatEnum format = column.getNumFormat();
        Class<?> fieldType = column.getField().getType();
        if (ExcelNumFormatEnum.PERCENTAGE.equals(format)) {
            try {
                if (BigDecimal.class.equals(fieldType)) {
                    if (StringUtils.isEmpty(valueStr)) {
                        valueStr = "-";
                    } else {
                        valueStr = new BigDecimal(100).multiply(new BigDecimal(valueStr)).toString() + "%";
                    }
                }
            } catch (Exception e) {
                LOGGER.error("设置属性出错", e);
            }
        }
        return valueStr;
    }

    /**
     * 通过方法名获取对象属性的值
     *
     * @param object     .
     * @param methodName .
     * @return .
     */
    private static Object getValue(Object object, String methodName) {
        Object value = null;
        if (StringUtils.isNotEmpty(methodName)) {
            try {
                Method method = object.getClass().getMethod(methodName);
                value = method.invoke(object);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("取值失败", e);
            }
        }
        return value;
    }

}
