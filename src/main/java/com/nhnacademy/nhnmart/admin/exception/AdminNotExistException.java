package com.nhnacademy.nhnmart.admin.exception;

public class AdminNotExistException extends RuntimeException {
    public AdminNotExistException(String message) {
        super( message + "해당 어드민은 존재하지 않습니다.");
    }

}
