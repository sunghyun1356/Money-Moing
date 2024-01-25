package com.MoneyMoing.MoneyServer.converter;

import com.MoneyMoing.MoneyServer.domain.HotelRoomType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// 문자열을 HotelRoomType으로 변환하는 Converter 구현체
import com.MoneyMoing.MoneyServer.domain.HotelRoomType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HotelRoomTypeConverter implements Converter<String, HotelRoomType> {

    // 문자열을 HotelRoomType으로 변환하는 메서드
    @Override
    public HotelRoomType convert(String source) {
        return HotelRoomType.fromParam(source);
    }
}
