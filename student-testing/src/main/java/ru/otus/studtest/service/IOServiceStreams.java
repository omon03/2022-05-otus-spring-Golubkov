package ru.otus.studtest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;


@Slf4j
@Service
public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner input;


    public IOServiceStreams(@Value("#{T(java.lang.System).out}") PrintStream output,
                            @Value("#{T(java.lang.System).in}") Scanner input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public void outputString(String s) {
        output.print(s);
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        outputString(prompt);
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            outputString("Enter the answer number in numbers!\n");
            return readIntWithPrompt(prompt);
        }
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

}
