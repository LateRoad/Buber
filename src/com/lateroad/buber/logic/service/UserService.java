package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.UserDAO;
import com.lateroad.buber.logic.entity.User;
import com.lateroad.buber.logic.exception.UserNotFoundException;

import java.io.IOException;

public class UserService {
    UserDAO userDAO = UserDAO.getInstance();

    public User userGetByEmailAndPassword(String login, String password) throws UserNotFoundException {
        User user = null;
        if ((user = userDAO.find(new User(login, password))) != null){
            return user;
        }
    throw new UserNotFoundException();
    }
}