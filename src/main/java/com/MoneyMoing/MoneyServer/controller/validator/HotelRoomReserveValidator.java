package com.MoneyMoing.MoneyServer.controller.validator;

import com.MoneyMoing.MoneyServer.controller.reservationDto.HotelRoomReserveRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class HotelRoomReserveValidator implements Validator {

    /**
     * HotelRoomReserveRequest 클래스를 지원하는지 여부를 확인합니다.
     *
     * @param clazz 검사 대상 클래스.
     * @return HotelRoomReserveRequest 클래스를 지원하면 true, 그렇지 않으면 false.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return HotelRoomReserveRequest.class.equals(clazz);
    }

    /**
     * HotelRoomReserveRequest 객체를 유효성 검사합니다.
     *
     * @param target 유효성 검사 대상 객체.
     * @param errors 유효성 검사 결과를 담을 Errors 객체.
     */
    @Override
    public void validate(Object target, Errors errors) {
        HotelRoomReserveRequest request = HotelRoomReserveRequest.class.cast(target);

        // checkInDate가 null인 경우 유효성 검사 오류를 추가합니다.
        if (Objects.isNull(request.getCheckInDate())) {
            errors.rejectValue("checkInDate", "NotNull", "checkInDate is null");
            return;
        }

        // checkOutDate가 null인 경우 유효성 검사 오류를 추가합니다.
        if (Objects.isNull(request.getCheckOutDate())) {
            errors.rejectValue("checkOutDate", "NotNull", "checkOutDate is null");
            return;
        }

        // checkOutDate가 checkInDate보다 이전인 경우 유효성 검사 오류를 추가합니다.
        if (!request.getCheckInDate().isBefore(request.getCheckOutDate())) {
            errors.rejectValue("checkOutDate", "Constraint Error", "checkOutDate is earlier than checkInDate");
        }
    }
}
