package ru.otus.studtest.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Getter
@Setter
@Component
@ConfigurationProperties("questions")
@Configuration
@RequiredArgsConstructor
public class ConfigProperties {

    private String fileCsv;
    private int theNumberOfCorrectAnswersForCredit;

}
