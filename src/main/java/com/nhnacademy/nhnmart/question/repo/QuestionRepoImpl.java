package com.nhnacademy.nhnmart.question.repo;

import com.nhnacademy.nhnmart.common.file.MultipartFileUpload;
import com.nhnacademy.nhnmart.question.domain.Answer;
import com.nhnacademy.nhnmart.question.domain.Question;
import com.nhnacademy.nhnmart.question.exception.QuestionNotExistException;
import com.nhnacademy.nhnmart.question.repo.impl.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class QuestionRepoImpl implements QuestionRepo {

    private final Map<String, List<String>> userQuestionMap = new HashMap<>();   // <userID,List<questionID>
    private final Map<String, Question> questionMap = new HashMap<>();
    private int questionIdCounter = 1;

    private final MultipartFileUpload multipartFileUpload;



    @Override
    public boolean existsQuestion(String questionId) {
        if(questionMap.containsKey(questionId)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Question get(String questionId) {
        if(!existsQuestion(questionId)){
            throw new QuestionNotExistException(questionId);
        }
        return questionMap.get(questionId);
    }

    @Override
    public List<Question> getAllByUserId(String userId) {
        List<Question> questionList = new ArrayList<>();
        List<String> questionIdList = userQuestionMap.get(userId);
        if(questionIdList == null){
            return null;
        }
        for (String s : questionIdList) {
            questionList.add(questionMap.get(s));
        }
        return questionList;
    }

    @Override
    public void create(String userId, String  userName, Question question, MultipartFile[] files) {
        String questionId = "q0000" + questionIdCounter++;
        question.setAuthor(userName);

        question.setQuestionId(questionId);
        question.setCurDateTime(LocalDateTime.now());

        if(userQuestionMap.containsKey(userId)){

            List<String> newquestionList = userQuestionMap.get(userId);
            newquestionList.add(questionId);

            userQuestionMap.replace(userId, newquestionList);
        }else {
            List<String> newquestionList = new ArrayList<>();
            newquestionList.add(questionId);
            userQuestionMap.put(userId, newquestionList);
        }
        if(files != null){
                for (MultipartFile file : files) {
                if (!file.isEmpty()) {

                    try {

                        String safeFileName = multipartFileUpload.saveFile(file, "/cs/uploads/" + questionId);
                        question.addFile("/cs/uploads/"+ questionId + "/" + safeFileName);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        questionMap.put(questionId, question);

    }

    @Override
    public List<Question> getAllUnanswered() {
        List<Question> questionList = new ArrayList<>();
        for(Map.Entry<String, Question> entry : questionMap.entrySet()) {
            Question question = entry.getValue();

            if(question.getAnswer() == null){
                questionList.add(question);
            }
        }

        return questionList;
    }

    @Override
    public void delete(String userId, String questionId) {
        if(!existsQuestion(questionId)){
            throw new QuestionNotExistException(questionId);
        }
        questionMap.remove(questionId);
        List<String> userquestionList = userQuestionMap.get(userId);
        userquestionList.remove(questionId);
        userQuestionMap.replace(userId, userquestionList);
    }

    @Override
    public void update(String questionId, Question question) {
        if(!existsQuestion(questionId)){
            throw new QuestionNotExistException(questionId);
        }
        questionMap.replace(questionId, question);

    }

    @Override
    public void addAnswer(String questionId, Answer answer) {
        Question question = questionMap.get(questionId);
        question.setAnswer(answer);
        questionMap.replace(questionId, question);
    }
}
