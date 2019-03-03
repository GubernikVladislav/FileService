package ru.gubernik.fileservice.service.email;

/**
 * Поток отправки сообщений
 */
public class SenderThread extends Thread {

    private EmailSender emailSender;
    private String activationCode;
    private String email;

    public SenderThread(EmailSender emailSender, String activationCode, String email){
        this.emailSender = emailSender;
        this.activationCode = activationCode;
        this.email = email;
    }

    @Override
    public void run() {
        emailSender.sendActivationCode(email, activationCode);
    }
}
