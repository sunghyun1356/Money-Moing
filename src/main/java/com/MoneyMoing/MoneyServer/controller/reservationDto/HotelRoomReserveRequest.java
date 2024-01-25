package com.MoneyMoing.MoneyServer.controller.reservationDto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class HotelRoomReserveRequest {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String name;
}
