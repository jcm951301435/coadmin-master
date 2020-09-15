package com.java.util;

import java.util.Collection;

/**
 * @author: jcm
 * @date: 2020/06/12
 */
public class CollectionUtils {

    /**
     * 判断是否为空
     *
     * @param collection .
     * @return .
     */
    public static boolean isEmpty(Collection<?> collection) {
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断是否不为空
     *
     * @param collection .
     * @return .
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
