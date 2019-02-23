package br.com.codenation.mapfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Motoboy não encontrado")
public class MotorcyclistNotFoundException extends RuntimeException {

    public MotorcyclistNotFoundException() {
    }

    public MotorcyclistNotFoundException(String message) {
        super(message);
    }

}
