package ru.gubernik.fileservice.dao;

import ru.gubernik.fileservice.model.User;

/**
 * Интерфейс Dao слоя пользователей
 */
public interface UserDao {

    /**
     * Добавление нового пользователя
     * @param user - пользователь
     */
    void addUser(User user);
}
