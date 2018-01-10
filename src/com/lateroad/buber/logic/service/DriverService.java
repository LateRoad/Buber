package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.DriverDAO;
import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;

public class DriverService implements CommonService {
    DriverDAO driverDAO = DriverDAO.getInstance();

    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return driverDAO.find(login, password);
    }
}
