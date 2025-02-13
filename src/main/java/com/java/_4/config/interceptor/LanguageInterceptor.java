package com.java._4.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

public class LanguageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String langHeader = request.getHeader("Accept-Language");
        Locale locale = (langHeader != null && !langHeader.isEmpty()) ? Locale.forLanguageTag(langHeader) : Locale.getDefault();
        LocaleContextHolder.setLocale(locale);
        return true;
    }
}
