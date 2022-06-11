package ru.otus.studtest.service;

import org.springframework.stereotype.Service;
import ru.otus.studtest.dto.Question;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionConverterImpl implements QuestionConverter{
    @Override
    public String convertQuestionToString(Question question) {
        AtomicInteger countQuestion = new AtomicInteger();
        StringBuilder resultString = new StringBuilder();
        resultString.append(question.getQuestion());
        resultString.append("\n");
        resultString.append("Answer options:\n");
        question.getAnswers().forEach(
                answer -> {
                    resultString
                            .append(countQuestion.incrementAndGet())
                            .append(") ")
                            .append(answer)
                            .append("\n");
                });
        return resultString.toString();
    }
}
