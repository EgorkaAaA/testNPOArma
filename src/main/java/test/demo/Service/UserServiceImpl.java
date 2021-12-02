package test.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.Entities.Users;
import test.demo.Exceptions.UserAllReadyExistsException;
import test.demo.Exceptions.UserNotAuthException;
import test.demo.Exceptions.UserNotFoundException;
import test.demo.Service.Interface.UserServiceInterface;
import test.demo.dao.UserDao;

@Service
public class UserServiceImpl implements UserServiceInterface {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Users addNewUser(Users user) throws UserAllReadyExistsException {
        Users userAdded = userDao.save(user);
        if(userAdded == null) {
            throw new UserAllReadyExistsException(String.format("Пользователь с именем: %s уже существует",user.getLogin()));
        }
        return userAdded;
    }

    @Override
    public Users updateUser(Users user) throws UserNotFoundException {
        if(userDao.findById(user.getUser_id()) == null) {
            throw new UserNotFoundException(String.format("Пользователь с id: %s, не найден",user.getUser_id()));
        }
        return userDao.update(user);
    }

    @Override
    public Users banUser(long id) throws UserNotFoundException {
        Users user = userDao.findById(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("Пользователь с id: %s, не найден",id));
        }
        user.setBanned(true);
        return userDao.update(user);
    }

    @Override
    public Users authUser(Users user) throws UserNotAuthException {
        Users userForAuth = userDao.authUser(user);
        if(userForAuth == null) {
            throw new UserNotAuthException("Не удалось авторизировать пользователя");
        }
        return userForAuth;
    }
}
