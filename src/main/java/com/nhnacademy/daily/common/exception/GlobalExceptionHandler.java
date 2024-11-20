package com.nhnacademy.daily.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(IdDuplicationException.class)
    public ResponseEntity<String> handleIdDuplicationException(IdDuplicationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);  // HTTP 409 상태 코드 반환
    }

    @ExceptionHandler(CodeDuplicationException.class)
    public ResponseEntity<String> handleCodeDuplicationException(CodeDuplicationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);  // HTTP 409 상태 코드 반환
    }
}
