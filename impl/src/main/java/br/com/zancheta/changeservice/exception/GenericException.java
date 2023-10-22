package br.com.zancheta.changeservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Getter
public class GenericException extends RuntimeException {
    private final HttpStatus status;

    public GenericException(final String message, HttpStatus status) {
        super(message);
        this.status = Optional.ofNullable(status).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
