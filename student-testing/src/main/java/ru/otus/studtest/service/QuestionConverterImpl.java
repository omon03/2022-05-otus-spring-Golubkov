package ru.otus.studtest.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.studtest.dto.Question;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class QuestionConverterImpl implements QuestionConverter{

    private final MessageSource messageSource;

    @Override
    public String convertQuestionToString(Question question) {
        String answerOptions = messageSource.getMessage("answer_options", null, Locale.getDefault());
        AtomicInteger countQuestion = new AtomicInteger();
        StringBuilder resultString = new StringBuilder();
        resultString.append(question.getQuestion());
        resultString.append(answerOptions);
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
