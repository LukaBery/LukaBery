package com.nhnacademy.nhnmart.common.config;

import com.nhnacademy.nhnmart.common.interceptor.login.AdminLoginInterceptor;
import com.nhnacademy.nhnmart.common.interceptor.login.UserLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final UserLoginInterceptor userLoginInterceptor;

    private final AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "resources/**", "/error", "/admin/**", "/cs/admin/**", "/cs/uploads/**");

        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login", "resources/**", "/error");
    }
}
