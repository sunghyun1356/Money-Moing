package com.MoneyMoing.MoneyServer.controller.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class ToDollarStringSerializer extends JsonSerializer<BigDecimal> {

    /**
     * BigDecimal을 달러 형식의 문자열로 직렬화합니다.
     *
     * @param value      직렬화할 BigDecimal 값.
     * @param gen        JsonGenerator 객체.
     * @param serializers SerializerProvider 객체.
     * @throws IOException 직렬화 중 발생하는 IOException.
     */
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // BigDecimal 값을 소수점 둘째 자리로 설정하여 문자열로 직렬화합니다.
        gen.writeString(value.setScale(2).toString());
    }
}
