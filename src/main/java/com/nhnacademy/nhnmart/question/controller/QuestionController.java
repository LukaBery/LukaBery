package com.nhnacademy.nhnmart.question.controller;

import com.nhnacademy.nhnmart.question.domain.Question;
import com.nhnacademy.nhnmart.question.exception.QuestionNotExistException;
import com.nhnacademy.nhnmart.question.exception.QuestionNotValidException;
import com.nhnacademy.nhnmart.question.repo.impl.QuestionRepo;
import com.nhnacademy.nhnmart.user.domain.User;
import com.nhnacademy.nhnmart.user.exception.UserNotFoundException;
import com.nhnacademy.nhnmart.user.repo.impl.UserLoginRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepo questionRepo;

    private final UserLoginRepo userLoginRepo;

    @GetMapping("/detail/{questionId}")
    public String viewQuestionDetail(@PathVariable(name="questionId", required=true) String questionId, Model model){
        if(!questionRepo.existsQuestion(questionId)){
            throw new QuestionNotExistException(questionId);
        }

        Question question = questionRepo.get(questionId);
        model.addAttribute("question", question);

        return "question/questionDetail";

    }

    @GetMapping("/create")
    public String viewCreatequestion(){

        return "question/questionCreate";
    }

    @PostMapping("/create")
    public String createquestion(@CookieValue(value = "USER-SESSION", required = false) String userSession,
                            @Valid @ModelAttribute("question") Question question, BindingResult bindingResult,
                            @RequestParam(value = "files", required = false) MultipartFile[] files
                            ){
        if(!userLoginRepo.isUserPresent(userSession)){
            throw new UserNotFoundException(userSession);
        }
        if(bindingResult.hasErrors()){
            throw  new QuestionNotValidException("제목, 분류, 내용");
        }

        User user = userLoginRepo.getUser(userSession);
        questionRepo.create(userSession, user.getName(), question, files);



    return "redirect:/mypage";
    }
}
