package com.java.common.converter;

import com.java.util.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * 字符串转换日期，用于 spring mvc 参数接收
 *
 * @author: jcm
 * @date: 2020/09/09
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringToDateConverter.class);

    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        source = source.trim();
        try {
            return DateUtils.parseDate(source, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            LOGGER.error("转换日期类型出错, 原值：" + source, e);
        }
        return null;
    }

}