package ru.otus.studtest.service;

import ru.otus.studtest.dto.Question;

public interface QuestionConverter {
    String convertQuestionToString(Question question);
}
