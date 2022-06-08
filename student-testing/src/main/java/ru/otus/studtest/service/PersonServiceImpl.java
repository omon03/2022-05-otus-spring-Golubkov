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
    private final IOService ioService;

    public Optional<Person> getByName(String name) {
        return personDao.findByName(name);
    }

    @Override
    public Person createPerson(String firstname, String lastname) {
        return personDao.createPerson(firstname, lastname);
    }

    @Override
    public Person createPersonFromIO() {
        return createPerson(
                ioService.readStringWithPrompt("\nYour firstname: "),
                ioService.readStringWithPrompt("Your lastname: ")
        );
    }
}
