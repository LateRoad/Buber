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
import com.lateroad.buber.validator.FormValidator;

import java.util.List;

public class DriverService implements CommonUserService<Driver> {
    private static final int COUNT_OF_ITERATION = 3;
    private static final int KILOMETERS_IN_ITERATION = 8;

    @Override
    public Driver authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        Driver driver;
        driver = DriverDAO.getInstance().find(login, password);
        CommonUserDAO.getInstance().setOnline(login, true);
        CommonUserDAO.getInstance().setRole(login, UserType.DRIVER);
        return driver;
    }

    @Override
    public Driver registration(Driver driver, String password, String confirmPassword) throws BuberSQLException, BuberLogicException {
        if (FormValidator.checkNessesaryFields(driver)) {
            if (FormValidator.checkPasswords(password, confirmPassword)) {
                if (!CommonUserDAO.getInstance().isExist(driver.getLogin())) {
                    CommonUserDAO.getInstance().insert(driver.getLogin(), password, driver.getRole());
                    UserDAO.getInstance().insert(driver.getLogin(), driver);
                    DriverDAO.getInstance().insert(driver.getLogin(), driver);
                    CommonUserDAO.getInstance().setOnline(driver.getLogin(), true);
                } else {
                    DriverDAO.getInstance().insert(driver.getLogin(), driver);
                }
            } else {
                throw new BuberLogicException("Passwords are different.");
            }
        } else {
            throw new BuberLogicException("Fill in all fields.");
        }
        return driver;
    }

    public List<Driver> getNearestDrivers(String clientLogin) throws BuberSQLException, BuberLogicException {
        List<Driver> nearestDrivers = null;
        for (int i = 1; i <= COUNT_OF_ITERATION && nearestDrivers == null; ++i) {
            nearestDrivers = DriverDAO.getInstance().find(clientLogin, KILOMETERS_IN_ITERATION * i);
        }
        return nearestDrivers;
    }


    public void acceptOrder(String login, int id) throws BuberSQLException, BuberLogicException {
        OrderDAO.getInstance().update(login, id);
    }
}
