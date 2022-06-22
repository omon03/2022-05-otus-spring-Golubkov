package ru.otus.studtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestRunner implements CommandLineRunner {

    private final TestProcessor testProcessor;

    @Override
    public void run(String... args) throws Exception {
        testProcessor.testing();
    }
}
