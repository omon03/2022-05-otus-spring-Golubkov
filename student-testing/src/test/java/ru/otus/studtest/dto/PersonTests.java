package ru.otus.studtest.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.studtest.dto.Person;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс Person")
class PersonTests {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        UUID uuid = UUID.randomUUID();
        String firstName = "Ivan";
        String lastName = "Petrov";
        int age = 42;

        Person person = new Person(uuid, firstName, lastName, age);

        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(age, person.getAge());
    }

}
