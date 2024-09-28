package com.nhnacademy.nhnmart.user.controller;

import com.nhnacademy.nhnmart.question.domain.Question;
import com.nhnacademy.nhnmart.question.repo.QuestionRepoImpl;
import com.nhnacademy.nhnmart.question.repo.impl.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final QuestionRepo qnaRepo;

    @GetMapping
    public String viewMypage(@CookieValue(value = "USER-SESSION", required = false) String userSession, Model model) {
        List<Question> questionList =  qnaRepo.getAllByUserId(userSession);
        questionList.sort(Comparator.comparing(Question::getCurDateTime).reversed());

        model.addAttribute("questionList", questionList);
        return "mypage/mypage";
    }





}
