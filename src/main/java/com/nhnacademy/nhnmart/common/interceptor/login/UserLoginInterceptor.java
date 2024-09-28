package com.nhnacademy.nhnmart.common.interceptor.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("USER-SESSION")) {
                    String sesssion = cookie.getName();
                    if(sesssion != null && !sesssion.isEmpty()){
                        return true;
                    }
                }
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}
