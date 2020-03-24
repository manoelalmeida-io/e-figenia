package br.com.squad2939.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Cannot find resource");
    }

    public ResourceNotFoundException(Long id) {
        super("Cannot find resource with id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
