package ru.gubernik.fileservice.service;

import ru.gubernik.fileservice.model.User;

/**
 * Интерфес работы с пользователями
 */
public interface UserService {

    /**
     * Добавление нового пользователя
     * @param user - пользователь
     */
    void addUser(User user);
}
