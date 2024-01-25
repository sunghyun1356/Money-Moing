package com.MoneyMoing.MoneyServer.domain.reservation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReserveService {

    public Long reserveHotelRoom(Long hotelId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate)
    {
        return 1_002_003_004L;
    }
}
