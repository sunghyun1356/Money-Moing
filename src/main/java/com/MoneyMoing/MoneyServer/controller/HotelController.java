package com.MoneyMoing.MoneyServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    /**
     * 모든 호텔 정보를 가져옵니다.
     */
    @GetMapping(path = "/hotels")
    public void getHotels()
    {
        System.out.println("호텔 목록 조회");
    }

    /**
     * 개장 여부에 따라 호텔 정보를 가져옵니다.
     */
    @GetMapping(path = "/hotels", params = "isOpen")
    public void getHotelsByOpen()
    {
        System.out.println("개장 중인 호텔 목록 조회");
    }
}
