package ru.otus.studtest.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.studtest.dto.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PersonDaoImpl implements PersonDao {

    private final List<Person> persons = new ArrayList<>();

    @Override
    public Person createPerson(String firstname, String lastname) {
        Person person = new Person(UUID.randomUUID(), firstname, lastname);
        persons.add(person);
        return person;
    }
}
