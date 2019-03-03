package ru.gubernik.fileservice.controller.registration;

import org.springframework.validation.BindingResult;
import ru.gubernik.fileservice.model.User;

public interface RegistrationController {

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

    /**
     * Активация пользователя по ссылке из письма
     * @param code - активационный код
     * @return перенаправляет на страницу логина
     */
    String activation(String code);
}
