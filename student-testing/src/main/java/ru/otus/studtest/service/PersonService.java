package ru.otus.studtest.service;

import ru.otus.studtest.dto.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> getByName(String name);
    Person createPerson(String firstname, String lastname);
    Person createPersonFromIO();

}
