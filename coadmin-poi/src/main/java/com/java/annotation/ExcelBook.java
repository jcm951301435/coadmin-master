package com.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel book 注解
 *
 * @author: jcm
 * @date: 2020/09/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelBook {

    /**
     * 标题
     * @return .
     */
    String title();

    /**
     * 导出是否显示序号
     * @return .
     */
    boolean showIndex() default true;

    /**
     * 导入模板起始行标
     * @return .
     */
    int importRowBeginCount() default 3;

    /**
     * 导入列起始
     * @return .
     */
    int importColBeginCount() default 0;

}
