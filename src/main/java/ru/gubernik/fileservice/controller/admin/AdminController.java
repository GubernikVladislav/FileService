package ru.gubernik.fileservice.controller.admin;

import org.springframework.ui.Model;

/**
 * Интерфейс контроллера администратора
 */
public interface AdminController {

    /**
     * Переход на страницу списка всех файлов в системе
     * @param model - модель
     * @return направляет на страницу списка файлов
     */
    String fileList(Model model);

    /**
     * Переход на страницу статистики
     * @param model - модель
     * @return направляет на страницу списка файлов
     */
    String statistic(Model model);
}
