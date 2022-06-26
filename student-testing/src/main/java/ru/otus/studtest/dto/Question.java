package ru.otus.studtest.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor(staticName="of")
public class Question {

    private UUID questionId;
    private String question;
    private int correctAnswer;
    private List<String> answers;

}
