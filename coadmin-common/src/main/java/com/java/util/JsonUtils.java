package com.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * json 工具类
 *
 * @author: jcm
 * @date: 2020/05/28
 */
public class JsonUtils {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

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
            LOGGER.error(String.format("object -> string 失败，原值：%s", object), e);
        }
        return resultStr;
    }

    /**
     * object -> map
     * @param object 源
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> objectToMap(Object object) {
        String objStr = objectToJson(object);
        Map<String, Object> resultMap = new HashMap<>(16);
        if (StringUtils.isBlank(objStr)) {
            return resultMap;
        }
        try {
            resultMap = MAPPER.readValue(objStr, Map.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("object -> map 失败，原值：%s", object), e);
            e.printStackTrace();
        }
        return resultMap;
    }

}
