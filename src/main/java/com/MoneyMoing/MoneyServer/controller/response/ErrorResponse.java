package com.MoneyMoing.MoneyServer.controller.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {

    private String errorMessage;

    public ErrorResponse(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
