package com.nhnacademy.nhnmart.question.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private String questionId;

    @NonNull
    @Length(min = 2, max = 200)
    private String title; //제목

    @NonNull
    private Category category; // 분류

    @Length(min = 0, max = 40000)
    private String content;  // 본문

    @NonNull
    private LocalDateTime curDateTime; // 작성일시

    @NonNull
    private String author; // 작성자

    private List<String> fileList = new ArrayList<>(); // 첨부파일 리스트

    private Answer answer; //답변

    public void addFile(String filName){
        fileList.add(filName);
    }


}
