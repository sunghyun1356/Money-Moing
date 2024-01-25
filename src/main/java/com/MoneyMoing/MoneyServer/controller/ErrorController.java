package com.MoneyMoing.MoneyServer.controller;

import com.MoneyMoing.MoneyServer.domain.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@RestController
public class ErrorController {

    private MessageSource messageSource;

    // 생성자를 통한 MessageSource 주입
    public ErrorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // "/error" 엔드포인트에 대한 핸들러 메서드
    @GetMapping(path = "/error")
    public void createError() {

        // 콘솔에 구분선 출력
        System.out.println("---------------------------------");

        // 현재 요청의 Locale 정보를 가져옴
        Locale locale = LocaleContextHolder.getLocale();

        // 메시지 소스를 사용하여 다국어 지원 메시지 가져오기
        String[] args = {"10"};
        String errorMessage = messageSource.getMessage("main.cart.tooltip", args, locale);

        // BadRequestException을 생성하고 메시지 설정
        BadRequestException badRequestException = new BadRequestException(errorMessage);

        // 현재 날짜를 가져와 로그 출력
        LocalDate errorDate = LocalDate.now();
        log.trace("trace log at, {}", errorDate);  // TRACE 레벨 로그 출력
        log.debug("debug log at, {}", errorDate);  // DEBUG 레벨 로그 출력
        log.info("info log at, {}", errorDate);    // INFO 레벨 로그 출력
        log.warn("warn log at, {}", errorDate);    // WARN 레벨 로그 출력
        log.error("error log at, {}, {}", errorDate, "errorMessage", badRequestException);  // ERROR 레벨 로그 출력

        // 생성한 BadRequestException을 던져서 예외를 발생시킴
        throw badRequestException;
    }
}