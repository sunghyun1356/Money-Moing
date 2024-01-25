package com.MoneyMoing.MoneyServer.controller.roomDto;

import com.MoneyMoing.MoneyServer.controller.serializer.ToDollarStringSerializer;
import com.MoneyMoing.MoneyServer.domain.HotelRoomType;
import com.MoneyMoing.MoneyServer.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Lombok 어노테이션인 @Getter를 사용하여 자동으로 게터 메서드를 생성합니다.
@Getter
public class HotelRoomResponse {

    // JSON 속성 이름을 "id"로 지정하고, 값의 직렬화 방법을 ToStringSerializer 클래스로 지정합니다.
    // Json 마셜링 단계에서 이름을 변경해서 저장하고 싶다면 JsonProperty를 사용
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final Long hotelRoomId;

    // 각 방의 번호를 나타내는 필드입니다.
    private final String roomNumber;

    // 호텔 방의 유형을 나타내는 열거형입니다.
    private final HotelRoomType hotelRoomType;

    // 원래 가격을 BigDecimal 형태로 표현하고, 값을 ToDollarStringSerializer 클래스로 직렬화합니다.
    @JsonSerialize(using = ToDollarStringSerializer.class)
    private final BigDecimal originalPrice;

    // 예약 정보를 담고 있는 리스트입니다.
    private final List<Reservation> reservations;

    // 생성자를 통해 객체를 초기화하는데 사용됩니다.
    public HotelRoomResponse(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        this.hotelRoomId = hotelRoomId;
        this.roomNumber = roomNumber;
        this.hotelRoomType = hotelRoomType;
        this.originalPrice = originalPrice;
        // 예약 정보를 담을 리스트를 초기화합니다.
        reservations = new ArrayList<>();
    }

    // 정적 팩토리 메서드를 사용하여 HotelRoomResponse 객체를 생성합니다.
    public static HotelRoomResponse of(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        return new HotelRoomResponse(hotelRoomId, roomNumber, hotelRoomType, originalPrice);
    }

    // 예약 정보를 추가하는 메서드입니다.
    public void reservedAt(LocalDate reservedAt) {
        reservations.add(new Reservation(IdGenerator.create(), reservedAt));
    }

    // Lombok 어노테이션인 @Getter를 사용하여 Reservation 클래스의 게터 메서드를 자동으로 생성합니다.
    @Getter
    private static class Reservation {

        // JSON 속성 이름을 "id"로 지정하고, 값의 직렬화 방법을 ToStringSerializer 클래스로 지정합니다.
        @JsonProperty("id")
        @JsonSerialize(using = ToStringSerializer.class)
        private final Long reservationId;

        // 예약된 날짜를 나타내는 필드입니다. JSON 형식을 "yyyy-MM-dd"로 지정합니다.
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private final LocalDate reservedDate;

        // 생성자를 통해 예약 정보를 초기화합니다.
        public Reservation(Long reservationId, LocalDate reservedDate) {
            this.reservationId = reservationId;
            this.reservedDate = reservedDate;
        }
    }
}

