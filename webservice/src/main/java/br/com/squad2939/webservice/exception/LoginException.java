package br.com.squad2939.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException{
    public LoginException() {
        super("Authentication failed");
    }

    public LoginException(String message) {
        super(message);
    }
}
