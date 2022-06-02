package ru.otus.studtest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import ru.otus.studtest.dto.Person;

import java.io.*;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class AppLauncher {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        File questionsFile = null;
        try {
            questionsFile = ResourceUtils.getFile("classpath:questions.csv");
            log.info("File Found : " + questionsFile.exists());
        } catch (FileNotFoundException e) {
            log.error("File Found : false");
            e.printStackTrace();
        }

        System.out.print("\nYour firstname: ");
        String firstname = readInput(reader);
        System.out.print("Your lastname: ");
        String lastname = readInput(reader);
        Person person = ;
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
