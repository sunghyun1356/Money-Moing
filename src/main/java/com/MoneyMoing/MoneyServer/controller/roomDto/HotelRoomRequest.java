package com.MoneyMoing.MoneyServer.controller.roomDto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class HotelRoomRequest {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String name;
}
