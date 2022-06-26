package ru.otus.studtest.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.studtest.dto.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class QuestionDaoImpl implements QuestionDao {

    private final List<Question> questions = new ArrayList<>();

    @Override
    public Question createQuestion(UUID questionId, String question, int correctAnswer, List<String> answers) {
        Question quest = Question.of(questionId, question, correctAnswer, answers);
        questions.add(quest);
        return quest;
    }

    @Override
    public List<Question> getAllQuestions() {
        return this.questions;
    }

    @Override
    public void setAllQuestions(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }

}
