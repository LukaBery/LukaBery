package com.nhnacademy.nhnmart.user.exception;

public class UserValidationFailException extends RuntimeException {

    public UserValidationFailException( ){
        super("아이디, 비밀번호가 일치하지 않습니다.");
    }
}
