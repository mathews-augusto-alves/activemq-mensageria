package br.com.mensageria.infrastructure.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.mensageria.domain.entity.CompositeResult;
import br.com.mensageria.domain.entity.ValidationResult;
import br.com.mensageria.infrastructure.interfaces.IValidation;

public class CompositeValidationHandler<T> {

    private final List<IValidation<T>> validations = new ArrayList<>();

    @SafeVarargs
    public final CompositeValidationHandler<T> compose(IValidation<T>... validations) {
        this.validations.addAll(Arrays.asList(validations));
        return this;
    }

    public CompositeResult handleValidation(T object) {
        CompositeResult result = new CompositeResult(Boolean.TRUE);
        for (IValidation<T> validation : validations) {
            ValidationResult validationResult = validation.validate(object);
            if (!validationResult.getIsValid()) {
                result.getErrors().add(validationResult.getMessage());
                result.setIsValid(Boolean.FALSE);
            }
        }
        return result;
    }
}
