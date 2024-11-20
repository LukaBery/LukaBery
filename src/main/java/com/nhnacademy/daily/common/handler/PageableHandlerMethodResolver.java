package com.nhnacademy.daily.common.handler;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageableHandlerMethodResolver implements HandlerMethodArgumentResolver {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 5;
    private static final int MAX_SIZE = 10;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return  Pageable.class.equals(parameter.getParameterType());

    }

    @Override
    public Pageable resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String pageString = webRequest.getParameter("page");
        String sizeString = webRequest.getParameter("size");
        int page = (pageString != null) ? Integer.parseInt(pageString) : DEFAULT_PAGE;
        int size = (sizeString != null && (Integer.parseInt(sizeString) <= MAX_SIZE)) ? Integer.parseInt(sizeString) : DEFAULT_SIZE;

        return PageRequest.of(page, size);
    }
}
