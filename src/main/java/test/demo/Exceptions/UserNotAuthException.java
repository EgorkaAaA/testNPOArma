package test.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotAuthException extends Exception {
    public UserNotAuthException(String message) {
        super(message);
    }
}
