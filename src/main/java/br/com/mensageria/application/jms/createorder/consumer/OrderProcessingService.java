package br.com.mensageria.application.jms.createorder.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mensageria.application.common.factory.OrderValidationFactory;
import br.com.mensageria.domain.entity.CompositeResult;
import br.com.mensageria.domain.entity.Order;

@Service
public class OrderProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingService.class);

    private final ObjectMapper objectMapper;
    private final OrderValidationFactory validationFactory;
    // private final OrderConfirmationService orderConfirmationService;
    // private final NotificationSender notificationSender;
    private final JmsTemplate topicJmsTemplate;

    public OrderProcessingService(
            @Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate,
            ObjectMapper objectMapper,
            OrderValidationFactory validationFactory
            // OrderConfirmationService orderConfirmationService,
            // NotificationSender notificationSender
            ) {
        this.objectMapper = objectMapper;
        this.validationFactory = validationFactory;
        // this.orderConfirmationService = orderConfirmationService;
        // this.notificationSender = notificationSender;
        this.topicJmsTemplate = topicJmsTemplate;
    }

    @JmsListener(destination = "orderQueue", containerFactory = "queueFactory")
    public void processOrder(String jsonOrder) {
        try {
            Order order = objectMapper.readValue(jsonOrder, Order.class);
            CompositeResult compositeResult = validationFactory.composeValidationOrderProcessing()
                    .handleValidation(order);
            if (!compositeResult.getIsValid()) {
                logger.warn("Order validation failed: {}", order);
                return;
            }

            confirmOrder(order);

        } catch (Exception e) {
            logger.error("Failed to process order: {}", jsonOrder, e);
            // Consider rethrowing or handling the exception based on business logic.
        }
    }

    public void confirmOrder(Order order) {
        String notificationMessage = "Order confirmed: " + order.getId();
        topicJmsTemplate.convertAndSend("notificationTopic", notificationMessage);
        logger.info("Order confirmation sent for order: {}", order.getId());
    }
}
