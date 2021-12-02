package test.demo.Service.Interface;

import test.demo.Entities.Users;
import test.demo.Exceptions.UserAllReadyExistsException;
import test.demo.Exceptions.UserNotAuthException;
import test.demo.Exceptions.UserNotFoundException;

public interface UserServiceInterface {
    Users addNewUser (Users user) throws UserAllReadyExistsException;

    Users updateUser (Users user) throws UserNotFoundException;

    Users banUser (long id) throws UserNotFoundException;

    Users authUser(Users user) throws UserNotAuthException;
}
