package test.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAllReadyExistsException extends Exception {
    public UserAllReadyExistsException(String message) {
        super(message);
    }
}
