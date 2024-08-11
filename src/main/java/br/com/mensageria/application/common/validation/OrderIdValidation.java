package br.com.mensageria.application.common.validation;

import org.springframework.stereotype.Component;

import br.com.mensageria.domain.entity.Order;
import br.com.mensageria.domain.entity.ValidationResult;
import br.com.mensageria.infrastructure.interfaces.IValidation;

@Component
public class OrderIdValidation implements IValidation<Order> {

    @Override
    public ValidationResult validate(Order input) {
        if(input.getId() == null) {
            return ValidationResult.builder()
                .isValid(false)
                .message("Pedido inválido.")
                .build();
        }
        return ValidationResult.builder().isValid(true).build();
    }

}
