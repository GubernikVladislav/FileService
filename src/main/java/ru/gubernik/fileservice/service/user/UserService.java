package ru.gubernik.fileservice.service.user;

import ru.gubernik.fileservice.model.User;

import java.util.List;

/**
 * Интерфес работы с пользователями
 */
public interface UserService {

    /**
     * Добавление нового пользователя
     * @param user - пользователь
     */
    void addUser(User user);

    /**
     * Получение списка пользователей
     * @return список пользователей
     */
    List<User> userList();

    /**
     * Активация пользователя по коду из письма
     * @param code активационный код
     */
    void activateUser(String code);
}
