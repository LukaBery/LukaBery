package com.nhnacademy.nhnmart.question.exception;

public class QuestionNotExistException extends RuntimeException{
    public QuestionNotExistException(String message) {
        super(message + "는 존재하지 않은 게시물입니다.");
    }
}
