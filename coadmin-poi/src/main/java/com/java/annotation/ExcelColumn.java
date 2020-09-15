package com.java.annotation;

import com.java.enumerate.ExcelNumFormatEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 列注解
 *
 * @author: jcm
 * @date: 2020/09/10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 字段名
     * @return .
     */
    String text() default "";

    /**
     * 宽度
     * @return .
     */
    int colWidth() default 20 * 256;

    /**
     * 字体颜色
     * @return .
     */
    short color() default 1;

    /**
     * 列号
     * @return .
     */
    int sort();

    /**
     * 数字格式化
     * @return .
     */
    ExcelNumFormatEnum numFormat() default ExcelNumFormatEnum.NULL;

}
