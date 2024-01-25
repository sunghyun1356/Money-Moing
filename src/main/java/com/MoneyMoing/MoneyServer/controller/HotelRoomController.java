package com.MoneyMoing.MoneyServer.controller;

import com.MoneyMoing.MoneyServer.controller.response.DeleteResultResponse;
import com.MoneyMoing.MoneyServer.controller.roomDto.HotelRoomIdResponse;
import com.MoneyMoing.MoneyServer.controller.roomDto.HotelRoomRequest;
import com.MoneyMoing.MoneyServer.controller.roomDto.HotelRoomResponse;
import com.MoneyMoing.MoneyServer.controller.roomDto.HotelRoomUpdateRequest;
import com.MoneyMoing.MoneyServer.domain.HotelRoomType;
import com.MoneyMoing.MoneyServer.utils.IdGenerator;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
public class HotelRoomController {

    private static final String HEADER_CREATE_AT = "X-CREATED-AT";
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * 특정 기간 동안 특정 호텔의 객실 정보를 조회합니다.
     * @param hotelId 호텔의 ID.
     * @param roomNumber 조회할 객실의 번호.
     * @param fromDate 기간의 시작 날짜 (선택 사항).
     * @param toDate 기간의 끝 날짜 (선택 사항).
     * @return 조회된 객실 정보를 담은 HotelRoomResponse.
     */
    @GetMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
    public HotelRoomResponse getHotelRoomByPeriod(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            @RequestParam(value = "fromDate", required=false) @DateTimeFormat(pattern = "yyyyMMdd")LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate toDate)
    {
        Long hotelRoomId = IdGenerator.create();
        BigDecimal originalPrice = new BigDecimal("130.00");

        HotelRoomResponse response = HotelRoomResponse.of(hotelRoomId, roomNumber, HotelRoomType.DOUBLE, originalPrice);
        if(Objects.nonNull(fromDate) && Objects.nonNull(toDate))
            fromDate.datesUntil(toDate.plusDays(1)).forEach(date -> response.reservedAt(date));
        return response;
    }

    /**
     * 호텔 객실을 삭제합니다.
     * @param hotelId 호텔의 ID.
     * @param roomNumber 삭제할 객실의 번호.
     * @return 삭제 결과를 나타내는 DeleteResultResponse.
     */
    @DeleteMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
    public DeleteResultResponse deleteHotelRoom(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber)
    {
        System.out.println("Delete Request. hotelId" + hotelId + ", roomNumber=" + roomNumber);
        return new DeleteResultResponse(Boolean.TRUE, "success");
    }

    /**
     * 특정 호텔 객실의 정보를 업데이트합니다.
     * @param hotelId 호텔의 ID.
     * @param roomNumber 업데이트할 객실의 번호.
     * @param hotelRoomUpdateRequest 업데이트할 객실 정보.
     * @param bindingResult 유효성 검사 결과.
     * @return 업데이트가 성공하면 HotelRoomIdResponse를 담은 ResponseEntity를 반환하고, 그렇지 않으면 잘못된 요청 응답을 반환합니다.
     */
    @PutMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
    public ResponseEntity<HotelRoomIdResponse> updateHotelRoomByNumber
    (
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            // hotelroomupdaterequest 인자 검사
            @Valid @RequestBody HotelRoomUpdateRequest hotelRoomUpdateRequest,
            BindingResult bindingResult)
            // bindingresult가 검증결과 조회가능 메서드 제공
    {
        if(bindingResult.hasErrors())
        {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = new StringBuilder("유효성 검사 오류")
                    .append(" 필드 : ").append(fieldError.getField())
                    .append(", 코드 : ").append(fieldError.getCode())
                    .append(", 메시지 : ").append(fieldError.getDefaultMessage())
                    .toString();
            System.out.println(errorMessage);
            return ResponseEntity.badRequest().build();
        }
        System.out.println(hotelRoomUpdateRequest.toString());
        HotelRoomIdResponse body = HotelRoomIdResponse.from(1_002_003_004L);
        return ResponseEntity.ok(body);
    }

    /**
     * 새로운 호텔 객실을 생성합니다.
     * @param hotelId 호텔의 ID.
     * @param hotelRoomRequest 새 객실의 정보.
     * @return HotelRoomIdResponse와 생성 시간이 담긴 ResponseEntity를 반환합니다.
     */
    @PostMapping(path = "/hotels/{hotelId}/rooms")
    public ResponseEntity<HotelRoomIdResponse> createHotelRoom(
            @PathVariable Long hotelId,
            @RequestBody HotelRoomRequest hotelRoomRequest
    )
    {
        System.out.println(hotelRoomRequest.toString());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HEADER_CREATE_AT, DATE_FORMATTER.format(ZonedDateTime.now()));
        HotelRoomIdResponse body = HotelRoomIdResponse.from(1_002_003_004L);
        return new ResponseEntity(body, headers, HttpStatus.OK);
    }
}
