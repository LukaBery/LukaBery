package com.nhnacademy.daily.common.exception;

public class IdDuplicationException extends RuntimeException{
    public IdDuplicationException(String message) {
        super(message + "중복된 아이디입니다.");
    }

}
