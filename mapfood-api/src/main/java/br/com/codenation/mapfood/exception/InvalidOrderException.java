package br.com.codenation.mapfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException() {
    }

    public InvalidOrderException(String message) {
        super(message);
    }
}
