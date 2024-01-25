package com.MoneyMoing.MoneyServer.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelRoomType {
    // single의 내부 속성인 param 값은 signle 문자열이다
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple"),
    QUAD("quad");

    // 각 HotelRoomType에 대한 매핑을 저장하는 paramMap
    private static final Map<String, HotelRoomType> paramMap = Arrays.stream(HotelRoomType.values())
            .collect(Collectors.toMap(
                    HotelRoomType::getParam,
                    Function.identity()
            ));

    private final String param;

    /**
     * HotelRoomType의 생성자입니다.
     *
     * @param param 해당 HotelRoomType의 문자열 표현.
     */
    HotelRoomType(String param) {
        this.param = param;
    }

    /**
     * 문자열로 표현된 param을 받아 해당하는 HotelRoomType을 반환합니다.
     *
     * @param param HotelRoomType의 문자열 표현.
     * @return 해당 문자열 표현과 일치하는 HotelRoomType.
     * @throws IllegalArgumentException 주어진 문자열에 해당하는 HotelRoomType이 없을 경우 예외를 던집니다.
     */
    // 언마셜링 과정에서 값변환에 사용되는 메서드를 지정하는 애너테이션
    @JsonCreator
    // json속성 값이 "single이면" from Param() 메서드가 HotelRoomType.SINGLE로 변경
    public static HotelRoomType fromParam(String param) {
        return Optional.ofNullable(param)
                .map(paramMap::get)
                .orElseThrow(() -> new IllegalArgumentException("Param is not valid"));
    }

    /**
     * HotelRoomType을 해당하는 문자열 표현으로 반환합니다.
     *
     * @return HotelRoomType의 문자열 표현.
     */
    // 마셜링 과정에서 값 변환에 사용되는 메서드를 지정하는 애너테이션
    @JsonValue
    public String getParam() {
        return this.param;
    }
}
