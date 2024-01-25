package com.MoneyMoing.MoneyServer.domain;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Hotel {
    private Long hotelId;
    private String name;
    private String address;
    private Integer roomCount;

    /**
     * Hotel 객체를 생성하는 생성자입니다.
     *
     * @param hotelId    호텔의 ID.
     * @param name       호텔의 이름.
     * @param address    호텔의 주소.
     * @param roomCount  호텔의 객실 수.
     * @throws IllegalArgumentException 호텔 생성 시 인자의 유효성을 확인하고, 유효하지 않으면 예외를 던집니다.
     */
    public Hotel(Long hotelId, String name, String address, Integer roomCount) {

        // 호텔 ID, 이름, 주소가 null인 경우 예외를 던집니다.
        if (Objects.isNull(hotelId) || Objects.isNull(name) || Objects.isNull(address)) {
            throw new IllegalArgumentException("Invalid hotel constraint");
        }

        // 객실 수가 null이거나 0 이하인 경우 예외를 던집니다.
        if (Objects.isNull(roomCount) || roomCount <= 0) {
            throw new IllegalArgumentException("Invalid room count");
        }

        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.roomCount = roomCount;
    }
}
