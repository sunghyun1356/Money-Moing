package com.MoneyMoing.MoneyServer.controller;

import com.MoneyMoing.MoneyServer.controller.roomDto.HotelRoomRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class RoomController {

    /**
     * 호텔에 여러 객실을 생성합니다.
     *
     * @param hotelId             호텔의 ID.
     * @param hotelRoomRequests   생성할 객실 정보 목록.
     * @param bindingResult       유효성 검사 결과.
     * @return ResponseEntity     생성된 객실에 대한 응답.
     */
    @PostMapping(path = "/hotels/{hotelId}/rooms")
    public ResponseEntity createHotelRoom(
            @PathVariable Long hotelId,
            @Valid @RequestParam List<HotelRoomRequest> hotelRoomRequests,
            BindingResult bindingResult
    )
    {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있으면 오류 응답을 반환합니다.
            return ResponseEntity.badRequest().body("Invalid request parameters");
        }

        // TODO: 호텔룸 생성 로직 추가

        // 생성이 성공하면 OK 응답을 반환합니다.
        return ResponseEntity.ok(null);
    }
}
