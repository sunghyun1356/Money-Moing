package com.MoneyMoing.MoneyServer.domain;

import lombok.Getter;

import java.util.StringJoiner;

@Getter
public class HotelRoomNumber {

    // 방 번호 구분자
    private static final String DELIMITER = "-";

    // 건물 코드
    private String buildingCode;

    // 방 번호
    private Long roomNumber;

    // 생성자
    public HotelRoomNumber(String buildingCode, Long roomNumber) {
        this.buildingCode = buildingCode;
        this.roomNumber = roomNumber;
    }

    // 문자열을 HotelRoomNumber 객체로 파싱하는 정적 메서드
    public static final HotelRoomNumber parse(String roomNumberId) {
        // DELIMITER를 기준으로 문자열을 분리
        String[] tokens = roomNumberId.split(DELIMITER);
        // 유효한 형식이 아닌 경우 예외 발생
        if (tokens.length != 2)
            throw new IllegalArgumentException("invalid roomNumberId format");
        // HotelRoomNumber 객체 생성 후 반환
        return new HotelRoomNumber(tokens[0], Long.parseLong(tokens[1]));
    }

    // HotelRoomNumber 객체를 문자열로 변환하는 메서드
    @Override
    public String toString() {
        // DELIMITER를 사용하여 필드들을 결합하여 문자열 반환
        return new StringJoiner(DELIMITER)
                .add(buildingCode)
                .add(roomNumber.toString())
                .toString();
    }
}