package br.com.mensageria.application.jms.createorder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mensageria.application.jms.createorder.usecase.SendOrderToQueueUseCase;
import br.com.mensageria.domain.entity.Order;
import br.com.mensageria.infrastructure.adapters.UseCaseHandler;
import br.com.mensageria.infrastructure.interfaces.IHandleRoute;


@RestController
@RequestMapping("/v1/orders")
public class CreateOrderController implements IHandleRoute<Order> {

    private final SendOrderToQueueUseCase sendOrderToQueueUseCase;
    private final UseCaseHandler<SendOrderToQueueUseCase, Order> useCaseHandler;

    public CreateOrderController(SendOrderToQueueUseCase sendOrderToQueueUseCase, UseCaseHandler<SendOrderToQueueUseCase, Order> useCaseHandler) {
        this.sendOrderToQueueUseCase = sendOrderToQueueUseCase;
        this.useCaseHandler = useCaseHandler;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> handleRoute(@RequestBody Order input) {
        return useCaseHandler.handle(sendOrderToQueueUseCase, input);
    }
}

