package ru.gubernik.fileservice.controller.file;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.gubernik.fileservice.config.authentification.UserPrincipal;

/**
 * Контроллер для работы с файлами
 */
public interface FileController {

    /**
     * Загрузка файла
     * @param model модель
     * @param file загружаемый файл
     * @param description - описание
     * @param user - пользователь, загрузивший файл
     * @return отправляет на страницу сервиса
     */
    String uploadFile(Model model,MultipartFile file, String description, UserPrincipal user);

    /**
     * Скачивание файла
     * @param fileName - название файла
     * @return запускает процесс скачивания файла
     */
    ResponseEntity<ByteArrayResource> download(String fileName);

}
