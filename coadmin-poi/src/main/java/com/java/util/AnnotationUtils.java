package com.java.util;

import com.java.annotation.ExcelBook;
import com.java.annotation.ExcelColumn;
import com.java.enumerate.ExcelNumFormatEnum;
import com.java.model.BaseExcelBook;
import com.java.model.BaseExcelColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jcm
 * @date: 2020/09/10
 */
public class AnnotationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationUtils.class);

    private static final Map<Class<?>, BaseExcelBook> EXCEL_CLASS_MAP = new HashMap<>();

    private static final String DEFAULT_TITLE = "Excel导出";

    private static final String INDEX_FIELD_NAME = "excelIndexField";

    private static final String INDEX_FIELD_TITLE = "序号";

    /**
     * 获取 cls 对应的 book 对象
     *
     * @param cls 处理类
     * @return book 对象
     */
    public static BaseExcelBook getExcelBook(Class<?> cls) {
        if (EXCEL_CLASS_MAP.containsKey(cls)) {
            return EXCEL_CLASS_MAP.get(cls);
        }
        BaseExcelBook bookEntity = initExcelBook(cls);
        EXCEL_CLASS_MAP.put(cls, bookEntity);
        return bookEntity;
    }

    /**
     * 根据 cls 构造 book 对象
     *
     * @param cls .
     * @return .
     */
    private static BaseExcelBook initExcelBook(Class<?> cls) {
        LOGGER.info("初始化表格类信息开始");
        String title = DEFAULT_TITLE;
        int importRowBeginCount = 3;
        int importColBeginCount = 0;
        boolean showIndex = true;
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ExcelBook) {
                title = ((ExcelBook) annotation).title();
                importRowBeginCount = ((ExcelBook) annotation).importRowBeginCount();
                importColBeginCount = ((ExcelBook) annotation).importColBeginCount();
                showIndex = ((ExcelBook) annotation).showIndex();
                break;
            }
        }
        List<BaseExcelColumn> columnList = initExcelColumns(cls, showIndex);
        return new BaseExcelBook(title, showIndex, importRowBeginCount, importColBeginCount, columnList);
    }

    /**
     * 根据 cls 构造 column 对象集合
     *
     * @param cls       .
     * @param showIndex .
     * @return .
     */
    private static List<BaseExcelColumn> initExcelColumns(Class<?> cls, boolean showIndex) {
        LOGGER.info("begin--初始化表格字段信息");
        List<BaseExcelColumn> columnList = new ArrayList<>();
        if (showIndex) {
            columnList.add(getIndexColumn());
        }
        List<Class<?>> superClasses = getSupperClasses(cls);
        for (Class<?> clsTmp : superClasses) {
            for (Field field : clsTmp.getDeclaredFields()) {
                BaseExcelColumn column = initExcelColumn(field);
                if (column != null) {
                    columnList.add(column);
                }
            }
        }
        LOGGER.info("end--初始化表格字段信息");
        return columnList;
    }

    /**
     * 序号行 column
     *
     * @return .
     */
    private static BaseExcelColumn getIndexColumn() {
        return new BaseExcelColumn(INDEX_FIELD_TITLE,
                5 * 256, (short) 1, 0,
                ExcelNumFormatEnum.NULL, INDEX_FIELD_NAME, null, null);
    }

    /**
     * 根据列信息构造 column
     *
     * @param field .
     * @return .
     */
    private static BaseExcelColumn initExcelColumn(Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            String fieldName = field.getName();
            String methodName = getGetMethodName(fieldName);
            if (StringUtils.isNotEmpty(fieldName)) {
                if (annotation instanceof ExcelColumn) {
                    ExcelColumn excelColumn = (ExcelColumn) annotation;
                    String text = excelColumn.text();
                    int colWidth = excelColumn.colWidth();
                    short color = excelColumn.color();
                    int sort = excelColumn.sort();
                    ExcelNumFormatEnum format = excelColumn.numFormat();
                    return new BaseExcelColumn(text, colWidth, color, sort, format, fieldName,
                            methodName, field);
                }
            }
        }
        return null;
    }

    /**
     * 根据属性拼接get方法
     * 注: 此处字段名必须为首字母小写(若不为小写 表示不符合规定 不处理此种情况)
     *
     * @param fieldName 字段名
     * @return 字段 get 方法名
     */
    private static String getGetMethodName(String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = (char) (chars[0] - 32);
        return "get" + new String(chars);
    }

    /**
     * 获取类 所有父类 包括自己
     *
     * @param cls .
     * @return .
     */
    private static List<Class<?>> getSupperClasses(Class<?> cls) {
        List<Class<?>> classList = new ArrayList<>();
        classList.add(cls);
        Class<?> superClass = cls.getSuperclass();
        while (superClass != null) {
            if ("java.lang.Object".equals(superClass.getSimpleName())) {
                break;
            }
            classList.add(superClass);
            superClass = superClass.getSuperclass();
        }
        return classList;
    }

    /**
     * 判断是否是序号字段
     *
     * @param fieldName .
     * @return .
     */
    public static boolean checkIsIndexFiled(String fieldName) {
        return INDEX_FIELD_NAME.equals(fieldName);
    }

}