package com.java.dto.query;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: jcm
 * @date: 2020/10/10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryCondition {

    /**
     * 过滤类型
     *
     * @return .
     */
    FilterType filterType() default FilterType.EQUAL;

    /**
     * 字段名数组
     *
     * @return .
     */
    String[] filterNames() default {};

    /**
     * 交联属性
     *
     * @return .
     */
    String join() default "";

    /**
     * 比较类型
     *
     * @return .
     */
    CompareClassEnum compareClass() default CompareClassEnum.STRING;

}
