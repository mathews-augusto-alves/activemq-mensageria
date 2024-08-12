package br.com.mensageria.application.common.validation;

import org.springframework.stereotype.Component;

import br.com.mensageria.domain.entity.Order;
import br.com.mensageria.domain.entity.ValidationResult;
import br.com.mensageria.infrastructure.interfaces.IValidation;

@Component
public class EmailValidation implements IValidation<Order> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public ValidationResult validate(Order input) {
        if (input == null || input.getEmail() == null || !input.getEmail().matches(EMAIL_REGEX)) {
            return ValidationResult.builder()
                .isValid(Boolean.FALSE)
                .message("O email informado não é válido.")
                .build();
        }
        return ValidationResult.builder().isValid(Boolean.TRUE).build();
    }

}
