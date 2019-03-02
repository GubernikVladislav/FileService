package ru.gubernik.fileservice.controller;

import org.springframework.ui.Model;

/**
 * Интерфейс контроллера администратора
 */
public interface AdminController {

    /**
     * Переход на страницу со списком пользователей
     * @return - имя представления списка пользователей
     */
    String userList(Model model);
}
