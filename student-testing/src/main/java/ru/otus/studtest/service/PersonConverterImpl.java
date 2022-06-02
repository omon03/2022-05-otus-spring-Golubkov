package ru.otus.studtest.service;

import ru.otus.studtest.dto.Person;

public class PersonConverterImpl implements PersonConverter {
    @Override
    public String convertPersonToString(Person person) {
        return person.getLastName() + " " + person.getFirstName();
    }
}
