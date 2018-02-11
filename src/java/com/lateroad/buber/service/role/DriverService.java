package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.CommonUserDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.validator.FormValidator;

import java.util.List;

/**
 * Class provides driver operations and incapsulate using of DAO layout.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class DriverService implements CommonUserService<Driver> {
    private static final int COUNT_OF_ITERATION = 3;
    private static final int KILOMETERS_IN_ITERATION = 8;


    /**
     * Perform a authentication operation.
     *
     * @return <code>Driver</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of authentication was broken.
     */
    @Override
    public Driver authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        Driver driver;
        driver = DriverDAO.getInstance().find(login, password);
        if (driver != null && driver.getRole().equals(UserType.CLIENT)) {
            CommonUserDAO.getInstance().setOnline(login, true);
            CommonUserDAO.getInstance().setRole(login, UserType.DRIVER);
        } else {
            throw new BuberLogicException("Wrong credentials.");
        }
        return driver;
    }

    /**
     * Perform a registration operation.
     *
     * @return <code>Driver</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of registration was broken.
     */
    @Override
    public Driver registration(Driver driver, String password, String confirmPassword) throws BuberSQLException, BuberLogicException {
        if (FormValidator.checkNecessaryFields(driver)) {
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

    /**
     * Perform a search of all drivers who nearby.
     *
     * @return <code>List of Drivers</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the operation was broken.
     */
    public List<Driver> getNearestDrivers(String clientLogin) throws BuberSQLException, BuberLogicException {
        List<Driver> nearestDrivers = null;
        for (int i = 1; i <= COUNT_OF_ITERATION && nearestDrivers == null; ++i) {
            nearestDrivers = DriverDAO.getInstance().find(clientLogin, KILOMETERS_IN_ITERATION * i);
        }
        return nearestDrivers;
    }

    /**
     * Set driver for order and set status as ACCEPT.
     *
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the operation was broken.
     */
    public void acceptOrder(String login, int id) throws BuberSQLException, BuberLogicException {
        OrderDAO.getInstance().update(login, id);
        OrderDAO.getInstance().update(id, OrderType.ACCEPTED);
    }
}
