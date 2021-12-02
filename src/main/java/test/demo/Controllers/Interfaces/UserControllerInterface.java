package test.demo.Controllers.Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import test.demo.Entities.Users;
import test.demo.Exceptions.UserAllReadyExistsException;
import test.demo.Exceptions.UserNotFoundException;

public interface UserControllerInterface {
    ResponseEntity<Users> addNewUser (@ModelAttribute Users user) throws UserAllReadyExistsException;

    ResponseEntity<Users> updateUser (@ModelAttribute Users user,long id) throws UserNotFoundException;

    ResponseEntity<Users> banUser(long id) throws UserNotFoundException;

    ResponseEntity<Users> authUser(@ModelAttribute Users user) throws Exception;
}
