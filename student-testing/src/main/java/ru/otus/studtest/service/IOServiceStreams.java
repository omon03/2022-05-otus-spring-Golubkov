package ru.otus.studtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner input;

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        outputString(prompt);
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

}
