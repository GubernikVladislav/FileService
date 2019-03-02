package ru.gubernik.fileservice.controller;

import org.springframework.validation.BindingResult;
import ru.gubernik.fileservice.model.User;

/**
 * Интерфейс основного контроллера
 */
public interface MainController {

    /**
     * Переход на главную страницу сервиса
     * @return - имя представления основной страницы сервиса
     */
    String service();

    /**
     * Переход на страницу регистрации
     * @param user - пустой объект пользователя
     * @return - имя представления регистрации
     */
    String registration(User user);

    /**
     * Добавление нового пользователя
     * @param user - данные пользователя
     * @param result - объект перехвата ошибок
     * @return - перенаправляет на страницу логина или обновляет страницу регистрации в случае ошибки
     */
    String addUser(User user, BindingResult result);

}
