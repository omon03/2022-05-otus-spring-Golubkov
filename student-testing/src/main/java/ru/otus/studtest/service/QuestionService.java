package ru.otus.studtest.service;

import ru.otus.studtest.dto.Question;


public interface QuestionService {

    Question getByNumber(int numberQuestion);

}
