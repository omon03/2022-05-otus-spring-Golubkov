package ru.otus.studtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Question {

    private UUID questionId;
    private String question;
    private int correctAnswer;
    private List<String> answers;

}
