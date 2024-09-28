package com.nhnacademy.nhnmart.question.repo.impl;

import com.nhnacademy.nhnmart.question.domain.Answer;
import com.nhnacademy.nhnmart.question.domain.Question;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionRepo {
    boolean existsQuestion(String qnaId);
    Question get(String qnaId);
    List<Question> getAllByUserId(String userId);
    void create(String userId, String userName, Question question, MultipartFile[] files);
    List<Question> getAllUnanswered();
    void delete(String userId, String questionId);
    void update(String userId, Question question);
    void addAnswer(String questionId, Answer answer);
}
