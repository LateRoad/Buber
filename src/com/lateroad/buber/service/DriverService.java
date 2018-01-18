package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.DriverDAO;
import com.lateroad.buber.entity.User;

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
