package ru.otus.studtest.service;

public interface InputService {
    int readInt();

    int readIntWithPrompt(String prompt);

    /**
     * Отправка строки и получение следующей
     * @param prompt
     * @return
     */
    String readStringWithPrompt(String prompt);  // TODO: добавить валидацию
}
