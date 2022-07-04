package ru.otus.studtest.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.otus.studtest.config.ConfigProperties;
import ru.otus.studtest.dao.QuestionDao;
import ru.otus.studtest.dto.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceCsv implements QuestionService{

    private static final int SKIP_COUNT_STRINGS = 1;  // skip the first line, header info
    private static final int INDEX_QUESTION = 0;
    private static final int INDEX_CORRECT_ANSWER = 1;
    private static final int INDEX_START_ANSWERS = 2;

    private final QuestionDao questionDao;
    private final ConfigProperties configProperties;

    @Override
    public List<Question> getAllQuestions() {
        if (questionDao.getAllQuestions().isEmpty()) {
            List<String[]> stringsCsv = parseStringsCsv();
            List<Question> questions = parseQuestions(stringsCsv);
            questionDao.setAllQuestions(questions);
        }
        return questionDao.getAllQuestions();
    }

    private List<String[]> parseStringsCsv() {
        List<String[]> stringsCsv = new ArrayList<>();
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // custom separator
        ClassPathResource resource = new ClassPathResource(configProperties.getFileCsv());

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(resource.getFile()))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(SKIP_COUNT_STRINGS)
                .build()) {
            stringsCsv = reader.readAll();
            log.debug("Read questions in CSV file: " + stringsCsv.size());
        } catch (IOException | CsvException e) {
            log.error("Oops!", e);
            e.printStackTrace();
        }

        return stringsCsv;
    }

    private List<Question> parseQuestions(List<String[]> stringsCsv) {
        final List<Question> questions = new ArrayList<>();
        stringsCsv.forEach(
            stringCsv -> questions.add(new Question(
                        UUID.randomUUID(),
                        stringCsv[INDEX_QUESTION],
                        Integer.parseInt(stringCsv[INDEX_CORRECT_ANSWER]),
                        parseAnswersFromStringCsv(stringCsv)
                    )
                )
        );
        return questions;
    }

    private List<String> parseAnswersFromStringCsv(String[] stringCsv) {
        final List<String> answers = new ArrayList<>();
        Stream.of(stringCsv)
                .skip(INDEX_START_ANSWERS)
                .forEach(answers::add);
        return answers;
    }

}
