package test.demo.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.demo.Controllers.Interfaces.UserControllerInterface;
import test.demo.DemoApplication;
import test.demo.Entities.Users;
import test.demo.Exceptions.UserAllReadyExistsException;
import test.demo.Exceptions.UserNotAuthException;
import test.demo.Exceptions.UserNotFoundException;
import test.demo.Service.UserServiceImpl;

@RestController
public class UserControllerImpl implements UserControllerInterface {

    private final UserServiceImpl userService;
    private Logger logs = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    public UserControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/users/add")
    @Override
    public ResponseEntity<Users> addNewUser(@ModelAttribute Users user) throws UserAllReadyExistsException {
        try {
            Users newUser = userService.addNewUser(user);
            logs.info("User created successful");
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        } catch (UserAllReadyExistsException e) {
            logs.error(e.getMessage());
            throw new UserAllReadyExistsException(e.getMessage());
        }
    }

    @PutMapping("/users/{id}/update")
    @Override
    public ResponseEntity<Users> updateUser(@ModelAttribute Users user, @PathVariable long id) throws UserNotFoundException {
        user.setUser_id(id);
        try {
            final Users userUpdated = userService.updateUser(user);
            logs.info("User update");
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            logs.error(e.getMessage());
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @PatchMapping("/users/{id}/ban")
    @Override
    public ResponseEntity<Users> banUser(@PathVariable long id) throws UserNotFoundException {
        try {
            final Users userBanned = userService.banUser(id);
            logs.info("User banned");
            return new ResponseEntity<>(userBanned, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            logs.error(e.getMessage());
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/auth")
    @Override
    public ResponseEntity<Users> authUser(@ModelAttribute Users user) throws Exception {
        try {
            final Users userAuth = userService.authUser(user);
            logs.info("User auth");
            return new ResponseEntity<>(userAuth, HttpStatus.OK);
        } catch (Exception e) {
            logs.error(e.getMessage());
            throw new UserNotAuthException(e.getMessage());
        }
    }
}
