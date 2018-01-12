package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.DriverDAO;
import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;

public class DriverService extends UserService {
    DriverDAO driverDAO = DriverDAO.getInstance();


    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return driverDAO.find(login, password);
    }
}
