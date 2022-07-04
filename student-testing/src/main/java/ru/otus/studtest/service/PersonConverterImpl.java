package ru.otus.studtest.service;

import org.springframework.stereotype.Service;
import ru.otus.studtest.dto.Person;

@Service
public class PersonConverterImpl implements PersonConverter {
    @Override
    public String convertPersonToString(Person person) {
        return person.getLastName() + " " + person.getFirstName();
    }
}
