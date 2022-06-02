package ru.otus.studtest.service;

import lombok.RequiredArgsConstructor;
import ru.otus.studtest.dao.QuestionDao;
import ru.otus.studtest.dto.Question;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao questionDao;

    @Override
    public Question getByNumber(int numberQuestion) {
        return questionDao.findByNumber(numberQuestion);
    }

}
