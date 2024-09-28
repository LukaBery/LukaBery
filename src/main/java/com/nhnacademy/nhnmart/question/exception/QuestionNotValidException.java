package com.nhnacademy.nhnmart.question.exception;

public class QuestionNotValidException extends RuntimeException{
    public QuestionNotValidException(String message){
        super(message + "필수 기입 항목을 입력해주세요");
    }
}
