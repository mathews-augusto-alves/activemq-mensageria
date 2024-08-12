package br.com.mensageria.infrastructure.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IExecution<O> {
    void execute(O object) throws JsonProcessingException ;
}
