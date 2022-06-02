package ru.otus.studtest.dao;

import org.springframework.stereotype.Service;
import ru.otus.studtest.dto.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonDaoImpl implements PersonDao {

    private static final List<Person> persons = new ArrayList<>();

    @Override
    public Person createPerson(String firstname, String lastname) {
        return createPerson(firstname, lastname, 0);
    }

    @Override
    public Person createPerson(String firstname, String lastname, int age) {
        Person person = new Person(UUID.randomUUID(), firstname, lastname, age);
        persons.add(person);
        return person;
    }

    public Optional<Person> findByName(String name) {
        return persons.stream().filter(person -> person.getFirstName().equals(name)).findFirst();
    }
}
