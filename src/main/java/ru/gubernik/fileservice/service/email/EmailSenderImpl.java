package ru.gubernik.fileservice.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActivationCode(String userEmail, String code){

        if(userEmail == null || userEmail.isEmpty()){
            return;
        }
        if(code == null || code.isEmpty()){
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();

        String mailText =
                "Подтвердите регистрацию на fileservice. Перейдите по ссылке http://lockalhost:8080/activation/" + code;

        message.setFrom("fileservice2019@gmail.com");
        message.setTo(userEmail);
        message.setSubject("Подтверждение регистрации");
        message.setText(mailText);

        javaMailSender.send(message);
    }
}
