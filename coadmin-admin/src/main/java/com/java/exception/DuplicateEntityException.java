package com.java.exception;

import org.springframework.util.StringUtils;

/**
 * sql 记录重复异常类
 *
 * @author: jcm
 * @date: 2020/05/28
 */
public class DuplicateEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateEntityException(Class<?> clazz, String field, String val) {
        super(DuplicateEntityException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }

    public DuplicateEntityException(String message) {
        super(message);
    }

}
