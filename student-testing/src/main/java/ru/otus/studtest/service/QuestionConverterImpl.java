package ru.otus.studtest.service;

import ru.otus.studtest.dto.Question;

public class QuestionConverterImpl implements QuestionConverter{
    @Override
    public String convertQuestionToString(Question question) {
        StringBuilder resultString = new StringBuilder();
        resultString.append(question.getQuestion());
        resultString.append("\n");
        resultString.append("Answer options:");
        question.getAnswers().forEach(
                answer -> {
                    resultString.append(answer);
                    resultString.append("\n");
                });
        return resultString.toString();
    }
}
