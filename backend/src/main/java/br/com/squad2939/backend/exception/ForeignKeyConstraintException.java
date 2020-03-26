package br.com.squad2939.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ForeignKeyConstraintException extends RuntimeException {

    public ForeignKeyConstraintException() {
        super("Operation violates foreign key constraint");
    }

    public ForeignKeyConstraintException(String message) {
        super(message);
    }
}
