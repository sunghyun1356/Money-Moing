package com.MoneyMoing.MoneyServer.converter;

import com.MoneyMoing.MoneyServer.domain.HotelRoomNumber;
import org.springframework.core.convert.converter.Converter;

// 문자열을 HotelRoomNumber으로 변환하는 Converter 구현체
public class HotelRoomNumberConverter implements Converter<String, HotelRoomNumber> {

    // 문자열을 HotelRoomNumber으로 변환하는 메서드
    @Override
    public HotelRoomNumber convert(String source) {
        return HotelRoomNumber.parse(source);
    }
}
