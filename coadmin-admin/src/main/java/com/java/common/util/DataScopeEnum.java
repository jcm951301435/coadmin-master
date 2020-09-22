package com.java.common.util;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
public enum DataScopeEnum {

    /**
     * 数据权限级别
     */
    ALL(0, "全部"),

    ONLY_SELF(1, "仅自己"),

    CUSTOMIZE(2, "自定义");


    private int value;

    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    DataScopeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataScopeEnum{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
