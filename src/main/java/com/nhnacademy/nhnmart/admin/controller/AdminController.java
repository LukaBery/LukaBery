package com.nhnacademy.nhnmart.admin.controller;

import com.nhnacademy.nhnmart.admin.repo.impl.AdminLoginRepo;
import com.nhnacademy.nhnmart.question.domain.Answer;
import com.nhnacademy.nhnmart.question.domain.Question;
import com.nhnacademy.nhnmart.question.repo.impl.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/answer")
public class AdminController {

    private final QuestionRepo questionRepo;

    private final AdminLoginRepo adminLoginRepo;

    @GetMapping("/{questionId}")
    public String answer(@PathVariable("questionId") String questionId, Model model) {

        Question question = questionRepo.get(questionId);
        model.addAttribute("question", question);

        return "answer/answerForm";
    }

    @PostMapping("/{questionId}")
    public String doAnswer(@PathVariable("questionId") String questionId,
                           @CookieValue(value = "ADMIN-SESSION", required = false) String adminId,
                           @RequestParam(value = "comment") String comment, Model model) {

        String adminName = adminLoginRepo.getAdmin(adminId).getName();
        Answer answer = new Answer(comment, LocalDateTime.now(), adminName);
        questionRepo.addAnswer(questionId, answer);

        return "redirect:/cs/admin";
    }
}
