package ru.otus.studtest.service;

import lombok.RequiredArgsConstructor;
import ru.otus.studtest.dao.QuestionDao;
import ru.otus.studtest.dto.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao questionDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

}
