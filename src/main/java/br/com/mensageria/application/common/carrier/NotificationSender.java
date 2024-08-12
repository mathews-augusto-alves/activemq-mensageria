package br.com.mensageria.application.common.carrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.mensageria.domain.entity.Order;

@Component
public class NotificationSender {

    private static final Logger logger = LoggerFactory.getLogger(NotificationSender.class);

    private final JmsTemplate jmsTemplate;

    public NotificationSender(@Qualifier("topicJmsTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendOrderConfirmation(Order order) {
        String notificationMessage = "Order confirmed: " + order.getId();
        jmsTemplate.convertAndSend("notificationTopic", notificationMessage);
        logger.info("Order confirmation sent for order: {}", order.getId());
    }
}
