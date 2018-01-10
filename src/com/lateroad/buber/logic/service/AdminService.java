package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.UserDAO;
import com.lateroad.buber.logic.entity.User;
import com.lateroad.buber.logic.exception.UserNotFoundException;

import java.sql.SQLException;

public class AdminService implements CommonService {
    UserDAO userDAO = UserDAO.getInstance();

    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return userDAO.find(login, password);
    }
}