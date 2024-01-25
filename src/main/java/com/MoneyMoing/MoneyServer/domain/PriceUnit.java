package com.MoneyMoing.MoneyServer.domain;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Getter
public class PriceUnit {

    private final Locale locale;

    public PriceUnit(Locale locale)
    {
        if (Objects.isNull(locale))
            throw new IllegalArgumentException("locale arg is null");

        this.locale = locale;

    }

    public String format(BigDecimal price){ // format 메서드는 bigdecimal 타입의 인자를 받아 포멧을 변경한다
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale); // numberformat 클래스는 숫자 문자열을 파싱하거나 특정형태로 포매팅
        return currencyFormat.format(
                Optional.ofNullable(price).orElse(BigDecimal.ZERO)
        );
    }
    public void validate()
    {
        if(Objects.isNull(locale))
            throw new IllegalStateException("locale is null");

        log.info("locale is [{}]", locale);
    }
}
