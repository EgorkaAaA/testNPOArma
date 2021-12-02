package test.demo.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.demo.Exceptions.UserAllReadyExistsException;
import test.demo.Exceptions.UserNotAuthException;
import test.demo.Exceptions.UserNotFoundException;

@ControllerAdvice
public class UsersExceptionHandel {
    @ExceptionHandler (UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundExceptionHandler(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(ex.getMessage());
    }

    @ExceptionHandler (UserNotAuthException.class)
    public ResponseEntity<Object> userNotAuthExceptionHandler(UserNotAuthException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler (UserAllReadyExistsException.class)
    public ResponseEntity<Object> userAllReadyExistsExceptionHandler(UserAllReadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


}
