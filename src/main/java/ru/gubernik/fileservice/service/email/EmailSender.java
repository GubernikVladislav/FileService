package ru.gubernik.fileservice.service.email;

/**
 * Сервис отправки email
 */
public interface EmailSender {

    /**
     * Отправка письма с кодом активации
     * @param userEmail - почта пользователя
     * @param code - активационный код
     */
    void sendActivationCode(String userEmail, String code);
}
