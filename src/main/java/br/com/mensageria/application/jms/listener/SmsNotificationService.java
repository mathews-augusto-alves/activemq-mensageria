package br.com.mensageria.application.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService {

    @JmsListener(destination = "notificationTopic", containerFactory = "topicFactory")
    public void sendSms(String notificationMessage) {
        // Lógica para enviar SMS
        System.out.println("SMS sent: " + notificationMessage);
    }
}
