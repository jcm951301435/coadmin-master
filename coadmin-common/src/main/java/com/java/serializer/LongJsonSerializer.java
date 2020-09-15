package com.java.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 自定义 Long 序列化
 *  因 js 精度问题，默认 Long 型使用 String 序列化，但部分 Long 型仍需要 Long 转换
 *  如：分页中 总数
 * @author: jcm
 * @date: 2020/09/15
 */
public class LongJsonSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeNumber(value);
        }
    }

}
