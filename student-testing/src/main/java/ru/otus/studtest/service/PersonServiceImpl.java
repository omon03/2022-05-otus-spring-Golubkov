package ru.otus.studtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.studtest.dao.PersonDao;
import ru.otus.studtest.dto.Person;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public Optional<Person> getByName(String name) {
        return personDao.findByName(name);
    }
}
