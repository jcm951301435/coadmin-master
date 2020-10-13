package com.java.util;

/**
 * 字符串工具类
 *
 * @author: jcm
 * @date: 2020/05/24
 */
public class StringUtils {

    /**
     * 是否为空
     *
     * @param obj .
     * @return .
     */
    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }

    /**
     * 是否为非空
     *
     * @param obj .
     * @return .
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 是否为非空且字符值不全为‘’
     *
     * @param str .
     * @return .
     */
    public static boolean isNotBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

    /**
     * 是否为空或字符值全为''
     *
     * @param str .
     * @return .
     */
    public static boolean isBlank(String str) {
        return !isNotEmpty(str);
    }

}
