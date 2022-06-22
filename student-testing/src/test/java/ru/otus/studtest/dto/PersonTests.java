package ru.otus.studtest.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.studtest.dto.Person;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Person")
class PersonTests {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        UUID uuid = UUID.randomUUID();
        String firstName = "Ivan";
        String lastName = "Petrov";

        Person person = new Person(uuid, firstName, lastName);

        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
    }

}
