package com.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 *
 * @author: jcm
 * @date: 2020/05/28
 */
public class JsonUtils {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * object -> string
     *
     * @param object 源
     * @return 结果
     */
    public static String objectToJson(Object object) {
        String resultStr = null;
        try {
            resultStr = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

}
