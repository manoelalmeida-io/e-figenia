package br.com.squad2939.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotFindResourceException extends RuntimeException {
    public CannotFindResourceException() {
        super("Cannot find resource");
    }

    public CannotFindResourceException(Long id) {
        super("Cannot find resource with id: " + id);
    }

    public CannotFindResourceException(String message) {
        super(message);
    }
}
