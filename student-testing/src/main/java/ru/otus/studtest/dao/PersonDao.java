package ru.otus.studtest.dao;

import ru.otus.studtest.dto.Person;

import java.util.Optional;

public interface PersonDao {

    Person createPerson(String firstname, String lastname);

}
