package br.com.mensageria.application.common.validation;

import org.springframework.stereotype.Component;

import br.com.mensageria.domain.entity.Order;
import br.com.mensageria.domain.entity.ValidationResult;
import br.com.mensageria.infrastructure.interfaces.IValidation;

@Component
public class EmailValidation implements IValidation<Order> {

    @Override
    public ValidationResult validate(Order input) {
        if (input == null || !input.toString().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            return ValidationResult.builder()
                .isValid(false)
                .message("O email informado não é válido.")
                .build();
        }
        return ValidationResult.builder().isValid(true).build();
    }

}
