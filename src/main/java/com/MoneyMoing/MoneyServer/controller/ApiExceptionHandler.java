package com.MoneyMoing.MoneyServer.controller;

import com.MoneyMoing.MoneyServer.controller.response.ErrorResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.FileSystemException;

public class ApiExceptionHandler {

    /**
     * BadRequestException을 처리하는 핸들러입니다.
     *
     * @param ex BadRequestException 객체.
     * @return ResponseEntity<ErrorResponse> 형태의 응답.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex)
    {
        System.out.println("에러 메시지 : " + ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * FileSystemException을 처리하는 핸들러입니다.
     *
     * @param e FileSystemException 객체.
     * @return ResponseEntity 형태의 응답.
     */
    @ExceptionHandler(FileSystemException.class)
    public ResponseEntity handleFileDownloadException(FileSystemException e)
    {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 그 외의 예외를 처리하는 핸들러입니다.
     *
     * @param ex Exception 객체.
     * @return ResponseEntity<ErrorResponse> 형태의 응답.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex)
    {
        return new ResponseEntity<>(
                new ErrorResponse("시스템 오류"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
