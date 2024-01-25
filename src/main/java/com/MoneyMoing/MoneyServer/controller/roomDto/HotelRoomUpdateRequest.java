package com.MoneyMoing.MoneyServer.controller.roomDto;

import com.MoneyMoing.MoneyServer.domain.HotelRoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class HotelRoomUpdateRequest {

    /**
     * 업데이트할 객실의 유형을 나타내는 열거형 변수.
     */
    @NotNull(message = "roomType can't be null")
    private HotelRoomType roomType;

    /**
     * 객실의 원래 가격을 나타내는 BigDecimal 변수.
     */
    @NotNull(message = "originalPrice can't be null")
    @Min(value = 0, message = "originalPrice must be larger than 0")
    private BigDecimal originalPrice;
}
