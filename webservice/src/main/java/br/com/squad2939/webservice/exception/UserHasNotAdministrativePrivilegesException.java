package br.com.squad2939.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserHasNotAdministrativePrivilegesException extends RuntimeException {

    public UserHasNotAdministrativePrivilegesException() {
        super("User has not administrative privileges");
    }

    public UserHasNotAdministrativePrivilegesException(String message) {
        super(message);
    }
}
