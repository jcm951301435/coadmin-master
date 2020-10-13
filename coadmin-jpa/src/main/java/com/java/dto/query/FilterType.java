package com.java.dto.query;

/**
 * @author: jcm
 * @date: 2020/10/10
 */
public enum FilterType {

    /**
     * 等于
     */
    EQUAL,

    /**
     * 不等于
     */
    NOT_EQUAL,

    /**
     * like
     */
    LIKE,

    /**
     * in
     */
    IN,

    /**
     * between
     */
    BETWEEN,

    /**
     * >
     */
    GREATER_THAN,

    /**
     * <
     */
    LESS_THAN,

    /**
     * >=
     */
    GREATER_THAN_OR_EQUALS,

    /**
     * <=
     */
    LESS_THAN_OR_EQUALS,

}
