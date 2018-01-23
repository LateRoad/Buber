package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.DriverDAO;
import com.lateroad.buber.entity.User;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverService extends UserService {
    private static final int COUNT_OF_ITERATION = 3;
    private static final int KILOMETERS_IN_ITERATION = 8;

    private DriverDAO driverDAO = DriverDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return driverDAO.find(login, password);
    }

    public List<User> getNearestDrivers(String clientLogin) throws SQLException {
        List<User> nearestDrivers = null;
        for (int i = 1; i <= COUNT_OF_ITERATION && nearestDrivers == null; ++i) {
            nearestDrivers = driverDAO.find(clientLogin, KILOMETERS_IN_ITERATION * i);
        }
        return nearestDrivers;
    }
}
