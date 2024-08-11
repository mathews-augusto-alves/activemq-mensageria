package br.com.mensageria.infrastructure.interfaces;

import br.com.mensageria.domain.entity.CompositeResult;

public interface IHandleCompositeValidation<O> {
    CompositeResult handleValidation(O object);
}
