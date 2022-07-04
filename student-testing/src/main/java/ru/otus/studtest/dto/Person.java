package ru.otus.studtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Person {

    private UUID uuID;
    private String firstName;
    private String lastName;

}
