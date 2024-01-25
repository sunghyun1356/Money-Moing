package com.MoneyMoing.MoneyServer.controller.resolver;

import com.MoneyMoing.MoneyServer.domain.ClientInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ClientInfoArgumentResolver implements HandlerMethodArgumentResolver {

    // 헤더의 키 값 상수 선언
    private static final String HEADER_CHANNEL = "X-MONEY-MOING-CHANNEL";
    private static final String HEADER_CLIENT_IP = "X-FOWARDED-FOR";

    // 해당 Resolver가 지원하는 파라미터인지 확인하는 메서드
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (ClientInfo.class.equals(parameter.getParameterType()))
            return true;
        return false;
    }

    // 파라미터의 해결을 수행하는 메서드
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 헤더에서 채널 정보와 클라이언트 IP 주소 정보를 추출
        String channel = webRequest.getHeader(HEADER_CHANNEL);
        String clientAddress = webRequest.getHeader(HEADER_CLIENT_IP);

        // 추출한 정보를 이용하여 ClientInfo 객체 생성 후 반환
        return new ClientInfo(channel, clientAddress);
    }
}
