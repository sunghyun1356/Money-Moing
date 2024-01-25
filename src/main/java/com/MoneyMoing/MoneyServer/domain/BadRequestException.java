package com.MoneyMoing.MoneyServer.domain;

import lombok.Getter;

import javax.management.RuntimeErrorException;

@Getter
public class BadRequestException extends RuntimeException {

    private String errorMessage;
    public BadRequestException(String errorMessage)
    {
        super();
        this.errorMessage = errorMessage;
    }
}
