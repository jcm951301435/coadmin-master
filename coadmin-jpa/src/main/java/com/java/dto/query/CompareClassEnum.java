package com.java.dto.query;

import java.util.Date;

/**
 * @author: jcm
 * @date: 2020/10/12
 */

public enum CompareClassEnum {

    /**
     *
     */
    DATE(Date.class),

    NUMBER(Long.class),

    STRING(String.class)
    ;

    private Class<? extends Comparable<?>> clz;

    public Class<? extends Comparable<?>> getClz() {
        return clz;
    }

    public void setClz(Class<? extends Comparable<?>> clz) {
        this.clz = clz;
    }

    CompareClassEnum(Class<? extends Comparable<?>> clz) {
        this.clz = clz;
    }
}
