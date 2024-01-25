package com.MoneyMoing.MoneyServer.controller;

import com.MoneyMoing.MoneyServer.controller.reservationDto.HotelRoomReserveRequest;
import com.MoneyMoing.MoneyServer.controller.roomDto.HotelRoomIdResponse;
import com.MoneyMoing.MoneyServer.controller.validator.HotelRoomReserveValidator;
import com.MoneyMoing.MoneyServer.domain.BadRequestException;
import com.MoneyMoing.MoneyServer.domain.reservation.ReserveService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")  // Endpoint에 공통된 경로를 지정합니다.
public class HotelRoomReserveController {

    private final ReserveService reserveService;

    public HotelRoomReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        // Validator를 등록합니다.
        binder.addValidators(new HotelRoomReserveValidator());
    }

    /**
     * 호텔 객실 예약을 처리하는 메소드입니다.
     *
     * @param hotelId        호텔 ID.
     * @param roomNumber     객실 번호.
     * @param reserveRequest 객실 예약 요청 DTO.
     * @param bindingResult  유효성 검사 결과를 담은 BindingResult 객체.
     * @return ResponseEntity<HotelRoomIdResponse> 형태의 응답.
     */
    @PostMapping("/{hotelId}/rooms/{roomNumber}/reserve")
    public ResponseEntity<HotelRoomIdResponse> reserveHotelRoomByRoomNumber(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            @Valid @RequestBody HotelRoomReserveRequest reserveRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = new StringBuilder(fieldError.getCode())
                    .append(" [").append(fieldError.getField()).append("] ")
                    .append(fieldError.getDefaultMessage())
                    .toString();
            System.out.println("Error: " + errorMessage);
            return ResponseEntity.badRequest().build();
        }

        // 예약 서비스를 통해 호텔 객실을 예약하고, 예약 ID를 받아옵니다.
        Long reservationId = reserveService.reserveHotelRoom(
                hotelId, roomNumber,
                reserveRequest.getCheckInDate(),
                reserveRequest.getCheckOutDate()
        );

        // 예약 ID를 응답으로 반환합니다.
        HotelRoomIdResponse body = HotelRoomIdResponse.from(reservationId);
        return ResponseEntity.ok(body);
    }


}
