package br.com.mensageria.application.common.factory;

import org.springframework.stereotype.Component;

import br.com.mensageria.application.common.validation.EmailValidation;
import br.com.mensageria.application.common.validation.OrderIdValidation;
import br.com.mensageria.domain.entity.Order;
import br.com.mensageria.infrastructure.adapters.CompositeValidationHandler;

@Component
public class OrderValidationFactory {

    public CompositeValidationHandler<Order> composeValidationOrderProcessing() {
        CompositeValidationHandler<Order> handler = new CompositeValidationHandler<>();
        handler.compose(
                new EmailValidation(),
                new OrderIdValidation());
        return handler;
    }
}
