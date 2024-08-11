package br.com.mensageria.infrastructure.adapters;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mensageria.domain.dto.ResponseError;
import br.com.mensageria.infrastructure.interfaces.IExecution;
import br.com.mensageria.infrastructure.interfaces.IHandlerUseCase;
import jakarta.servlet.http.HttpServletRequest;


@Component
public class UseCaseHandler<I extends IExecution<P>, P> implements IHandlerUseCase<I, P> {

    private static final String GENERIC_ERROR_MESSAGE = "Não foi possível concluir a requisição.";
    private final HttpServletRequest request;

    public UseCaseHandler(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<?> handle(I execution, P param) {
        try {
            execution.execute(param);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return buildErrorResponse(exception);
        }
    }

    private ResponseEntity<ResponseError> buildErrorResponse(Exception exception) {
        ResponseError responseError = createResponseError(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    private ResponseError createResponseError(Exception exception) {
        return ResponseError.builder()
                .error(GENERIC_ERROR_MESSAGE)
                .message(exception.getMessage() != null ? exception.getMessage() : "Erro genérico.")
                .path(request.getRequestURI()) 
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
