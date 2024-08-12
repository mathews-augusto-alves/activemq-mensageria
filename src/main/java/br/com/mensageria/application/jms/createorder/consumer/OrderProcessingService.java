package br.com.mensageria.application.jms.createorder.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mensageria.application.common.carrier.NotificationSender;
import br.com.mensageria.application.common.factory.OrderValidationFactory;
import br.com.mensageria.domain.entity.CompositeResult;
import br.com.mensageria.domain.entity.Order;

@Service
public class OrderProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingService.class);

    private final ObjectMapper objectMapper;
    private final OrderValidationFactory validationFactory;
    private final NotificationSender notificationSender;

    public OrderProcessingService(
            @Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate,
            ObjectMapper objectMapper,
            OrderValidationFactory validationFactory,
            NotificationSender notificationSender
            ) {
        this.objectMapper = objectMapper;
        this.validationFactory = validationFactory;
        this.notificationSender = notificationSender;
    }

    @JmsListener(destination = "orderQueue", containerFactory = "queueFactory")
    public void processOrder(String jsonOrder) {
        try {
            Order order = objectMapper.readValue(jsonOrder, Order.class);
            CompositeResult compositeResult = validationFactory.composeValidationOrderProcessing()
                    .handleValidation(order);
            if (!compositeResult.getIsValid()) {
                logger.warn("Order validation failed: {}", order);
                logger.info("Message removed from queue.");
                return;
            }

            this.notificationSender.sendOrderConfirmation(order);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed to process order: {}", jsonOrder, e);
            logger.info("Message removed from queue.");
        }
    }
}
