package com.MoneyMoing.MoneyServer.server;

import jakarta.servlet.*;

import java.io.IOException;

// 로깅 작업을 수행하는 필터 구현체
public class LoggingFilter implements Filter {

    // 필터 초기화 시 호출되는 메서드
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 작업을 수행 (필요시 구현)
    }

    // 실제 필터링 작업을 수행하는 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("선처리 작업");  // 요청 전에 수행할 작업 (전처리)
        chain.doFilter(request, response);  // 다음 필터로 요청 전달 또는 요청에 대한 서블릿으로 진행
        System.out.println("후처리 작업");  // 응답 전에 수행할 작업 (후처리)
    }

    // 필터 종료 시 호출되는 메서드

}