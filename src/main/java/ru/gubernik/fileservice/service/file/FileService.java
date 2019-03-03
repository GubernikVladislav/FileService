package ru.gubernik.fileservice.service.file;

import org.springframework.web.multipart.MultipartFile;
import ru.gubernik.fileservice.model.File;
import ru.gubernik.fileservice.model.User;

import java.util.List;

/**
 * Сервис работы с файлами
 */
public interface FileService {

    /**
     * Закгрузка нового файла
     * @param uploadFile - загружаемый файл
     * @param description - описание файла
     * @param owner - владалец файла
     */
    void upload(MultipartFile uploadFile, String description, User owner);

    /**
     * Получение списка всех файлов
     * @return список файлов
     */
    List<File> fileList();

    /**
     * Получение файла по имени
     * @param fileName - имя файла
     * @return - файл
     */
    File getByFileName(String fileName);

    /**
     * Получение списка файлов пользователя
     * @param userName - имя пользователя
     * @return список файлов
     */
    List<File> getUserFilesList(String userName);
}
