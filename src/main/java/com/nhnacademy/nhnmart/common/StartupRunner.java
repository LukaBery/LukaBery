package com.nhnacademy.nhnmart.common;

import com.nhnacademy.nhnmart.question.domain.Answer;
import com.nhnacademy.nhnmart.question.domain.Question;
import com.nhnacademy.nhnmart.question.repo.impl.QuestionRepo;
import com.nhnacademy.nhnmart.user.domain.User;
import com.nhnacademy.nhnmart.user.repo.impl.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.nhnacademy.nhnmart.question.domain.Category.COMPLAINT;
import static com.nhnacademy.nhnmart.question.domain.Category.SUGGESTION;

@Component
public class StartupRunner implements ApplicationRunner {
    @Autowired
    private UserLoginRepo userLoginRepo;

    @Autowired
    private QuestionRepo questionRepo;




    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User("1", "1", "정성훈");
        userLoginRepo.addUser(user);

        Question question = new Question("", "1제목입니당", COMPLAINT,"본문입니당",  LocalDateTime.now(), user.getName(),  null, null);
        Question question1 = new Question("", "2제목입니당", SUGGESTION,"본문입니당",  LocalDateTime.now(), user.getName(), null, null);
        Question question2 = new Question("", "3제목입니당", SUGGESTION,"본문입니당",  LocalDateTime.now(), user.getName(), null, new Answer("이이이이이", LocalDateTime.now(), "이한빈"));
        Question question3 = new Question("", "4제목입니당", SUGGESTION,"본문입니당",  LocalDateTime.now(), user.getName(), null, null);

        questionRepo.create(user.getId(), user.getName(), question, null);
        questionRepo.create(user.getId(), user.getName(), question1, null);
        questionRepo.create(user.getId(), user.getName(), question2, null);
        questionRepo.create(user.getId(), user.getName(), question3, null);

    }
}
