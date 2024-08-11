package br.com.mensageria.application.jms.createorder.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mensageria.domain.entity.Order;
import br.com.mensageria.infrastructure.interfaces.IExecution;

@Service
public class SendOrderToQueueUseCase implements IExecution<Order>{

    private final JmsTemplate queueJmsTemplate;
    private final ObjectMapper objectMapper;

    public SendOrderToQueueUseCase(@Qualifier("queueJmsTemplate") JmsTemplate queueJmsTemplate, ObjectMapper objectMapper) {
        this.queueJmsTemplate = queueJmsTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Order order) {
        try {
            String jsonOrder = objectMapper.writeValueAsString(order);
            queueJmsTemplate.convertAndSend("orderQueue", jsonOrder);
        } catch (Exception e) {
            // Handle exception
        }
    }
}
