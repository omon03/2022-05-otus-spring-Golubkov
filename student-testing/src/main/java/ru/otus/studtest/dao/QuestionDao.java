package ru.otus.studtest.dao;

import ru.otus.studtest.dto.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionDao {

    Question createQuestion(UUID questionId, String question, int correctAnswer, List<String> answers);
    List<Question> getAllQuestions();
    void setAllQuestions(List<Question> questions);

}
