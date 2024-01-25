package com.MoneyMoing.MoneyServer.domain;

import org.springframework.stereotype.Service;

@Service
public class HotelSearchService {

    public Hotel getHotelById(Long hotelId)
    {
        return new Hotel(hotelId,
                "The Continental",
                "1 Wall St, New York, NY 10005",
                250);
    }

}
