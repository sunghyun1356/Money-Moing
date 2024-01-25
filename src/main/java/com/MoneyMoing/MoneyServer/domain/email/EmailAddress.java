package com.MoneyMoing.MoneyServer.domain.email;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

// 이메일 주소를 나타내는 클래스
@Getter
@ToString
public class EmailAddress {

    // 이메일 주소에서 사용되는 구분자
    private static final String AT = "@";

    // 이름, 도메인 파트, 로컬 파트를 나타내는 필드
    private String name;
    private String domainPart;
    private String localPart;

    // 생성자
    public EmailAddress(String name, String localPart, String domainPart) {
        this.name = name;
        this.domainPart = domainPart;
        this.localPart = localPart;
    }

    // 객체를 문자열로 변환하는 메서드
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // 이름이 존재하면 추가
        if (Objects.nonNull(name))
            sb.append(name).append(" ");

        // 최종적으로 "<로컬파트@도메인파트>" 형식으로 반환
        return sb.append("<").append(localPart).append(AT).append(domainPart).append(">").toString();
    }
}