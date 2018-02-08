package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.CommonUserDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;

import java.util.List;

public class DriverService implements CommonUserService<Driver> {
    private static final int COUNT_OF_ITERATION = 3;
    private static final int KILOMETERS_IN_ITERATION = 8;

    @Override
    public Driver authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        Driver driver = null;
        driver = DriverDAO.getInstance().find(login, password);
        CommonUserDAO.getInstance().setOnline(login, true);
        CommonUserDAO.getInstance().setRole(login, UserType.DRIVER);
        return driver;
    }

    @Override
    public Driver registration(String... params) throws BuberSQLException, BuberLogicException {
        Driver driver = null;
        if (!CommonUserDAO.getInstance().isExist(params[0])) {
            driver = new Driver(params[0], UserType.DRIVER, params[2], params[3], params[4], params[5], params[6], params[7]);
            CommonUserDAO.getInstance().insert(driver.getLogin(), params[1], driver.getRole());
            UserDAO.getInstance().insert(driver.getLogin(), driver);
            DriverDAO.getInstance().insert(driver.getLogin(), driver);
            CommonUserDAO.getInstance().setOnline(driver.getLogin(), true);
        } else {
            DriverDAO.getInstance().insert(driver.getLogin(), driver);
        }
        return driver;
    }

    public List<Driver> getNearestDrivers(String clientLogin) throws BuberSQLException {
        List<Driver> nearestDrivers = null;
        for (int i = 1; i <= COUNT_OF_ITERATION && nearestDrivers == null; ++i) {
            nearestDrivers = DriverDAO.getInstance().find(clientLogin, KILOMETERS_IN_ITERATION * i);
        }
        return nearestDrivers;
    }


    public void acceptOrder(String login, int id) throws BuberSQLException {
        OrderDAO.getInstance().update(login, id);
    }
}
