package ru.otus.studtest.service;

import ru.otus.studtest.dto.Person;

import java.util.Optional;

public interface PersonService {

    Person createPerson(String firstname, String lastname);

}
