package com.java.common.util;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
public class CommonUtils {

//    public static <T> void wrapperCreateTime(QueryWrapper<T> wrapper, Date[] createTimeArray) {
//        if (createTimeArray != null && createTimeArray.length > 0) {
//            Date createTimeBegin = createTimeArray[0];
//            Date createTimeEnd = null;
//            if (createTimeArray.length > 1) {
//                createTimeEnd = createTimeArray[1];
//            }
//            if (createTimeBegin != null) {
//                wrapper.ge("create_time", createTimeBegin);
//            }
//            if (createTimeEnd != null) {
//                wrapper.le("create_time", createTimeEnd);
//            }
//        }
//    }

    public static <T> void criteriaCreateTime(CriteriaBuilder criteriaBuilder) {

    }

}
