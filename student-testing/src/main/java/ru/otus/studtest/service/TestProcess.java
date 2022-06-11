package ru.otus.studtest.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.studtest.config.ConfigProperties;
import ru.otus.studtest.dto.Person;
import ru.otus.studtest.dto.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
@Setter
public class TestProcess implements CommandLineRunner {

    private PersonService personService;
    private QuestionService questionService;
    private QuestionConverter questionConverter;
    private IOService ioService;
    private ConfigProperties configProperties;

    private Map<Question, Integer> questionsAnswers;


    @Override
    public void run(String... args) {
        int theNumberOfCorrectAnswersForCredit = configProperties.getTheNumberOfCorrectAnswersForCredit();
        List<Question> questions = questionService.getAllQuestions();
        Person student = personService.createPersonFromIO();

        questions.forEach(q -> questionsAnswers.put(q, askQuestion(q)));
        int resultTest = getResultTest(questionsAnswers);
        if (resultTest >= theNumberOfCorrectAnswersForCredit) {
            ioService.outputString(String.format(
                                            "You passed the test.\nCongratulations!\nYour score is %d out of %d.\n\n",
                                            resultTest,
                                            theNumberOfCorrectAnswersForCredit));
        } else {
            ioService.outputString("You didn't pass the test.\nPlease try again later.\n\n");
        }
    }

    private int askQuestion(Question question) {
        ioService.outputString(questionConverter.convertQuestionToString(question));
        return ioService.readIntWithPrompt("Ответ: ");
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
