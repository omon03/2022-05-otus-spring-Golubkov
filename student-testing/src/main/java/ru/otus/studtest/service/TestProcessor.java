package ru.otus.studtest.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.studtest.config.ConfigProperties;
import ru.otus.studtest.dto.Person;
import ru.otus.studtest.dto.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@AllArgsConstructor
public class TestProcessor {

    private final PersonService personService;
    private final PersonConverter personConverter;
    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final IOService ioService;
    private final ConfigProperties configProperties;
    private final MessageSource messageSource;




    public void testing() {
        final Map<Question, Integer> questionsAnswers = new HashMap<>();
        final List<Question> questions = questionService.getAllQuestions();
        int theNumberOfCorrectAnswersForCredit = configProperties.getTheNumberOfCorrectAnswersForCredit();
        Person student = personService.createPersonFromIO();
        log.info("Create object Person: " + personConverter.convertPersonToString(student));

        questions.forEach(q -> questionsAnswers.put(q, askQuestion(q)));
        int resultTest = getResultTest(questionsAnswers);
        if (resultTest >= theNumberOfCorrectAnswersForCredit) {
            ioService.outputString(String.format(
                    messageSource.getMessage("you_passed_the_test", null, Locale.getDefault()),
                    resultTest,
                    theNumberOfCorrectAnswersForCredit));
        } else {
            ioService.outputString(String.format(
                    messageSource.getMessage("you_didnt_pass_the_test", null, Locale.getDefault()),
                    resultTest,
                    theNumberOfCorrectAnswersForCredit));
        }
    }

    private int askQuestion(Question question) {
        ioService.outputString(questionConverter.convertQuestionToString(question));
        return ioService.readIntWithPrompt(messageSource.getMessage("your_answer", null, Locale.getDefault()));
    }

    private boolean checkAnswer(Question question, int answer) {
        return question.getCorrectAnswer() == answer;
    }

    private int getResultTest(Map<Question, Integer> questionsAnswers) {
        AtomicInteger result = new AtomicInteger(0);
        questionsAnswers.forEach((question, answer) -> {
            if (checkAnswer(question, answer)) {
                result.incrementAndGet();
            }
        });
        return result.get();
    }
}
