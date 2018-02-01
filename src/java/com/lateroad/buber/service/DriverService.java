package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.DriverDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.exception.BuberLogicException;

import java.sql.SQLException;
import java.util.List;

public class DriverService extends UserService {
    private static final int COUNT_OF_ITERATION = 3;
    private static final int KILOMETERS_IN_ITERATION = 8;

    private DriverDAO driverDAO = DriverDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException, BuberLogicException {
        User driver = driverDAO.find(login, password);
        setOnline(driver.getLogin(), true);
        return driver;
    }

    public List<User> getNearestDrivers(String clientLogin) throws SQLException {
        List<User> nearestDrivers = null;
        for (int i = 1; i <= COUNT_OF_ITERATION && nearestDrivers == null; ++i) {
            nearestDrivers = driverDAO.find(clientLogin, KILOMETERS_IN_ITERATION * i);
        }
        return nearestDrivers;
    }

    public void acceptOrder(String login, int id) throws SQLException {
        OrderDAO.getInstance().update(login, id);
    }
}
