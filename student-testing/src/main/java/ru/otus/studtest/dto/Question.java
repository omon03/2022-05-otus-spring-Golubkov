package ru.otus.studtest.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@RequiredArgsConstructor(staticName="of")
public class Question {

    UUID questionId;
    String question;
    int correctAnswer;
    List<String> answers;

}
