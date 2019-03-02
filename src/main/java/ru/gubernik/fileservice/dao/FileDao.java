package ru.gubernik.fileservice.dao;

import ru.gubernik.fileservice.model.File;

import java.util.List;

/**
 * Dao для работы с базой данных файлов
 */
public interface FileDao {

    /**
     * Добавление нового файла
     * @param file файл
     */
    void saveFile(File file);

    /**
     * Получение списка всех файлов
     * @return список файлов
     */
    List<File> findAll();

    /**
     * Получение файла по названию
     * @param fileName - название файла
     * @return файл
     */
    File findByFileName(String fileName);
}
