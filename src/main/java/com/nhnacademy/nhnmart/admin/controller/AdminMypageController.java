package com.nhnacademy.nhnmart.admin.controller;


import com.nhnacademy.nhnmart.question.domain.Question;
import com.nhnacademy.nhnmart.question.repo.impl.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AdminMypageController {

    private final QuestionRepo questionRepo;

    @GetMapping("/cs/admin")
    public  String viewAdminMypage(@CookieValue(value = "ADMIN-SESSION", required = false) String adminSession, Model model) {
        List<Question> questionList = questionRepo.getAllUnanswered();
        questionList.sort(Comparator.comparing(Question::getCurDateTime));
        model.addAttribute("questionList", questionList);
        return "mypage/adminMypage";
    }
}
