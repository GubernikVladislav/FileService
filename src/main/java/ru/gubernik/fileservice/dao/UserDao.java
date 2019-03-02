package ru.gubernik.fileservice.dao;

import ru.gubernik.fileservice.model.User;

import java.util.List;

/**
 * Интерфейс Dao слоя пользователей
 */
public interface UserDao {

    /**
     * Добавление нового пользователя
     * @param user - пользователь
     */
    void addUser(User user);

    /**
     * Получение списка всех пользователей
     * @return List список пользователей
     */
    List<User> findAll();
}
