package ru.otus.studtest.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.studtest.dto.Question;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class QuestionConverterImpl implements QuestionConverter {

    private final MessageSource messageSource;

    @Override
    public String convertQuestionToString(Question question) {
        String answerOptions = messageSource.getMessage("answer_options", null, Locale.getDefault());
        int countQuestion = 0;
        List<String> answers = question.getAnswers();
        StringBuilder resultString = new StringBuilder()
                .append(question.getQuestion())
                .append(answerOptions);

        for (String answer : answers) {
            resultString
                    .append(countQuestion++)
                    .append(") ")
                    .append(answer)
                    .append("\n");
        }
        return resultString.toString();
    }
}
