package ru.otus.studtest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import ru.otus.studtest.dao.PersonDao;
import ru.otus.studtest.dto.Person;
import ru.otus.studtest.service.PersonService;

import java.io.*;


@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class AppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(AppLauncher.class, args);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        File questionsFile;
        try {
            questionsFile = ResourceUtils.getFile("classpath:questions.csv");
            log.info("File Found: " + questionsFile.exists());
        } catch (FileNotFoundException e) {
            log.error("File Found: false");
            e.printStackTrace();
        }
    }

    private static String readInput(@NonNull BufferedReader reader) {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        log.info(line);
        return line;
    }
}
