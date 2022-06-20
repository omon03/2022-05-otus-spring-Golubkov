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

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@AllArgsConstructor
@Setter
public class TestProcess implements CommandLineRunner {

    private PersonService personService;
    private PersonConverter personConverter;
    private QuestionService questionService;
    private QuestionConverter questionConverter;
    private IOService ioService;
    private ConfigProperties configProperties;
    private MessageSource messageSource;

    private Map<Question, Integer> questionsAnswers;


    @Override
    public void run(String... args) {
        int theNumberOfCorrectAnswersForCredit = configProperties.getTheNumberOfCorrectAnswersForCredit();
        List<Question> questions = questionService.getAllQuestions();
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
