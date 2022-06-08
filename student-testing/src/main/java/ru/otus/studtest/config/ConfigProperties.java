package ru.otus.studtest.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("questions")
@RequiredArgsConstructor
public class ConfigProperties {
    private String fileCsv;
    private int theNumberOfCorrectAnswersForCredit;
}
