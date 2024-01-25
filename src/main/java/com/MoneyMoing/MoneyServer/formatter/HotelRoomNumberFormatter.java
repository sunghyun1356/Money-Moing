package com.MoneyMoing.MoneyServer.formatter;

import com.MoneyMoing.MoneyServer.domain.HotelRoomNumber;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

//@Component
// Formatter 인터페이스를 구현한 HotelRoomNumberFormatter 클래스
public class HotelRoomNumberFormatter implements Formatter<HotelRoomNumber> {

    // 문자열을 HotelRoomNumber로 파싱하는 메서드
    @Override
    public HotelRoomNumber parse(String text, Locale locale) throws ParseException {
        return HotelRoomNumber.parse(text);
    }

    // HotelRoomNumber를 문자열로 변환하는 메서드
    @Override
    public String print(HotelRoomNumber hotelRoomNumber, Locale locale) {
        return hotelRoomNumber.toString();
    }
}