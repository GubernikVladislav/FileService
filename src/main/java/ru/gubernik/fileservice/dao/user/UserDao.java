package ru.gubernik.fileservice.dao.user;

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

    /**
     * Получение пользователя по имени
     * @param userName имя пользователя
     * @return объект пользователя
     */
    User getUserByName(String userName);
}
