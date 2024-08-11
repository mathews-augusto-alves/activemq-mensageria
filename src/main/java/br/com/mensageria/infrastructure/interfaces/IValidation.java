package br.com.mensageria.infrastructure.interfaces;

import br.com.mensageria.domain.entity.ValidationResult;

public interface IValidation<I> {
    ValidationResult validate(I input);
}
