package br.com.mensageria.application.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    @JmsListener(destination = "notificationTopic", containerFactory = "topicFactory")
    public void sendEmail(String notificationMessage) {
        // Lógica para enviar email
        System.out.println("Email sent: " + notificationMessage);
    }
}
