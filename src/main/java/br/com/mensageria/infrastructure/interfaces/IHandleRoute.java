package br.com.mensageria.infrastructure.interfaces;

import org.springframework.http.ResponseEntity;

public interface IHandleRoute<I> {
    ResponseEntity<?> handleRoute(I input);
}
