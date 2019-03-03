package ru.gubernik.fileservice.controller;

import org.springframework.ui.Model;
import ru.gubernik.fileservice.config.authentification.UserPrincipal;

/**
 * Интерфейс основного контроллера
 */
public interface MainController {

    /**
     * Переход на главную страницу сервиса
     * @return - имя представления основной страницы сервиса
     */
    String service(UserPrincipal user, Model model);

    /**
     * Переход на страницу пользователя
     * @param userPrincipal - активный пользователь
     * @param pageOwner - имя владельца страницы
     * @param model - модель
     * @return возвращает представление страницы пользователя pageOwner
     */
    String userPage( UserPrincipal userPrincipal, String pageOwner, Model model);


    /**
     * Переход на страницу со списком пользователей
     * @return - имя представления списка пользователей
     */
    String userList(UserPrincipal userPrincipal, Model model);
}
