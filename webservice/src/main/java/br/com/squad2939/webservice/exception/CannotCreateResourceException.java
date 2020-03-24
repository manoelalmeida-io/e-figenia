package br.com.squad2939.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotCreateResourceException extends RuntimeException {
    public CannotCreateResourceException() {
        super("Error creating resource");
    }

    public CannotCreateResourceException(String message) {
        super(message);
    }
}
