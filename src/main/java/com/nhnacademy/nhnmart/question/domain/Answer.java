package com.nhnacademy.nhnmart.question.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Answer {

    private String comment;
    private LocalDateTime creDate;
    private String adminName;
}
