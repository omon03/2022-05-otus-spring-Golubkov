package ru.otus.studtest.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.otus.studtest.config.ConfigProperties;
import ru.otus.studtest.dto.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class QuestionDaoImpl implements QuestionDao {

    private final static List<Question> questions = new ArrayList<>();
    private static final int SKIP_COUNT_STRINGS = 1;  // skip the first line, header info
    private static final int INDEX_QUESTION = 0;
    private static final int INDEX_CORRECT_ANSWER = 1;
    private static final int INDEX_ANSWER_1 = 2;
    private static final int INDEX_ANSWER_2 = 3;
    private static final int INDEX_ANSWER_3 = 4;
    private static final int INDEX_ANSWER_4 = 5;
    private static final int INDEX_ANSWER_5 = 6;

    public QuestionDaoImpl(@NotNull ConfigProperties configProperties) {
        List<String[]> stringsCsv = parseStringsCsv(configProperties.getFileCsv());
        createQuestions(stringsCsv);
    }


    // TODO: можно вынести в отдельный парсер
    private List<String[]> parseStringsCsv(String fileCsvName) {
        List<String[]> stringsCsv = new ArrayList<>();
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator

        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileCsvName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(SKIP_COUNT_STRINGS)
                .build()) {
            stringsCsv = reader.readAll();
            log.info("Read questions in CSV file: " + stringsCsv.size());
//            stringsCsv.forEach(x -> System.out.println(Arrays.toString(x)));
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return stringsCsv;
    }

    private void createQuestions(List<String[]> stringsCsv) {
        stringsCsv.forEach(
                stringCsv -> {
                    List<String> answers = new ArrayList<>(Arrays.asList(
                            stringCsv[INDEX_ANSWER_1],
                            stringCsv[INDEX_ANSWER_2],
                            stringCsv[INDEX_ANSWER_3],
                            stringCsv[INDEX_ANSWER_4],
                            stringCsv[INDEX_ANSWER_5]));
                    questions.add(Question.of(
                            UUID.randomUUID(),
                            stringCsv[INDEX_QUESTION],
                            Integer.parseInt(stringCsv[INDEX_CORRECT_ANSWER]),
                            answers
                            )
                    );
                });
    }

    @Override
    public Question createQuestion(UUID questionId, String question, int correctAnswer, List<String> answers) {
        Question quest = Question.of(questionId, question, correctAnswer, answers);
        questions.add(quest);
        return quest;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questions;
    }

}
