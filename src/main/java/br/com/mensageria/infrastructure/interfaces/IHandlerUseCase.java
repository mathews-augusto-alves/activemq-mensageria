package br.com.mensageria.infrastructure.interfaces;

import org.springframework.http.ResponseEntity;

public interface IHandlerUseCase<I extends IExecution<P>, P> {
    ResponseEntity<?> handle(I execution, P param);
}
