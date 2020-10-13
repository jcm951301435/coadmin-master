package com.java.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: jcm
 * @date: 2020/10/10
 */
@Slf4j
public class ReflectUtils {

    /**
     * 根据属性拼接get方法
     * 注: 此处字段名必须为首字母小写(若不为小写 表示不符合规定 不处理此种情况)
     *
     * @param fieldName 字段名
     * @return 字段 get 方法名
     */
    public static String getGetMethodName(String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = (char) (chars[0] - 32);
        return "get" + new String(chars);
    }

    /**
     * 反射获取对象字段的值
     * @param field .
     * @param obj .
     * @return .
     */
    public static Object getFiledValue(Field field, Object obj) {
        Object value = null;
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            value = field.get(obj);
        } catch (IllegalAccessException e) {
            log.error("无法获取对象{}中字段{}的值", obj, field.getName());
            e.printStackTrace();
        } finally {
            field.setAccessible(accessible);
        }
        return value;
    }

    /**
     * 获取类 所有父类 包括自己
     *
     * @param cls .
     * @return .
     */
    public static List<Class<?>> getSupperClasses(Class<?> cls) {
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
     * 获取所有字段
     *
     * @param cls       .
     * @param hasSupper 是否包含父类
     * @return .
     */
    public static Set<Field> getAllFields(Class<?> cls, boolean hasSupper) {
        Set<Field> fieldSet = new HashSet<>();
        if (hasSupper) {
            List<Class<?>> superClasses = ReflectUtils.getSupperClasses(cls);
            for (Class<?> clsTmp : superClasses) {
                fieldSet.addAll(Arrays.asList(clsTmp.getDeclaredFields()));
            }
        } else {
            fieldSet.addAll(Arrays.asList(cls.getDeclaredFields()));
        }
        return fieldSet;
    }

    /**
     * 获取所有字段 包括父类
     *
     * @param cls       .
     * @return .
     */
    public static Set<Field> getAllFieldsWithSupper(Class<?> cls) {
        return getAllFields(cls, true);
    }

}
