package br.com.zancheta.changeservice.exception;

import org.springframework.http.HttpStatus;

public class BillException extends GenericException {
    public BillException() {
        super("Invalid value for client", HttpStatus.BAD_REQUEST);
    }
}
