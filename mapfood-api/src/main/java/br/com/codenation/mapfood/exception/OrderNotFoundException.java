package br.com.codenation.mapfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pedido não encontrado")
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {}

    public OrderNotFoundException(String message) {
        super(message);
    }
}
