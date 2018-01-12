package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.UserDAO;
import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;

public class AdminService extends UserService {
    UserDAO userDAO = UserDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return userDAO.find(login, password);
    }
}