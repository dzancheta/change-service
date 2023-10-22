package br.com.zancheta.changeservice.exception;

import org.springframework.http.HttpStatus;

public class InsufficientCoinsException extends GenericException {
    public InsufficientCoinsException() {
        super("insufficient coins", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
