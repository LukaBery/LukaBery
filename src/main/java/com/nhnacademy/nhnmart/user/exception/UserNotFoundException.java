package com.nhnacademy.nhnmart.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message + "는 존재하지 않는 유저입니다.");
    }
}
