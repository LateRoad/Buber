package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.entity.User;

import java.sql.SQLException;

public class AdminService extends UserService {
    private UserDAO userDAO = UserDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return userDAO.find(login, password);
    }
}