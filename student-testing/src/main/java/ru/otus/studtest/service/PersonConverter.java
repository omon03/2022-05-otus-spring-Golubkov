package ru.otus.studtest.service;

import ru.otus.studtest.dto.Person;

public interface PersonConverter {
    String convertPersonToString(Person person);
}
