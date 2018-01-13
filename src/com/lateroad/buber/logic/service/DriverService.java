package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.DriverDAO;
import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;
import java.util.List;

public class DriverService extends UserService {
    DriverDAO driverDAO = DriverDAO.getInstance();
    private List<User> nearestDrivers;


    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return driverDAO.find(login, password);
    }

    public List<User> getNearestDrivers(String from) throws SQLException {
        return driverDAO.find(from.split(", ")[1]);
    }
}
