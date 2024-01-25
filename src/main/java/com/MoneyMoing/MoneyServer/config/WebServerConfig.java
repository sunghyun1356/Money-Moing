package com.MoneyMoing.MoneyServer.config;

import com.MoneyMoing.MoneyServer.controller.resolver.ClientInfoArgumentResolver;
import com.MoneyMoing.MoneyServer.converter.HotelRoomNumberConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebServerConfig implements WebMvcConfigurer {

    // HttpMessageConverter 설정
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());  // JSON 변환을 위한 Jackson HttpMessageConverter 등록
        converters.add(new MappingJackson2XmlHttpMessageConverter());  // XML 변환을 위한 Jackson HttpMessageConverter 등록
    }

    // LocaleResolver 빈 설정
    @Bean(value = "localeResolver")
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.KOREAN);  // 기본 로케일을 한국어로 설정
        return acceptHeaderLocaleResolver;
    }

    // Interceptor 설정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");  // 요청 파라미터 "locale"을 통해 언어 변경
        registry.addInterceptor(localeChangeInterceptor)
                .excludePathPatterns("/favicon.ico")  // 특정 패턴은 제외
                .addPathPatterns("/**");  // 모든 패턴에 대해 인터셉트
    }

    // Formatter 설정
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new HotelRoomNumberConverter());  // HotelRoomNumberConverter를 등록하여 문자열을 HotelRoomNumber로 변환
    }

    // HandlerMethodArgumentResolver 설정
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ClientInfoArgumentResolver());  // ClientInfoArgumentResolver를 등록하여 컨트롤러 메서드의 파라미터에 ClientInfo 객체를 주입
    }

    // CharacterEncodingFilter 빈 설정
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> defaultCharacterEncodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");  // 인코딩을 UTF-8로 설정
        encodingFilter.setForceEncoding(true);  // 강제로 설정한 인코딩을 사용하도록 강제

        FilterRegistrationBean<CharacterEncodingFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(encodingFilter);
        filterBean.addInitParameter("paramName", "paramValue");  // 필터에 초기 파라미터 설정
        filterBean.addUrlPatterns("*");  // 모든 URL 패턴에 필터 적용
        filterBean.setOrder(1);  // 필터 순서 설정
        return filterBean;
    }
}
