package com.lateroad.buber.service.role;

import com.lateroad.buber.command.impl.database.dao.LocationInfoDAO;
import com.lateroad.buber.command.impl.database.dao.OrderDAO;
import com.lateroad.buber.command.impl.database.dao.UserDAO;
import com.lateroad.buber.command.impl.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
import com.lateroad.buber.service.CommonUserService;

import java.util.List;

public class DriverService implements CommonUserService<CurrentModel> {
    private static final int COUNT_OF_ITERATION = 3;
    private static final int KILOMETERS_IN_ITERATION = 8;

    @Override
    public CurrentModel authentication(String login, String password) throws BuberSQLException {
        CurrentModel model = null;
        Driver driver = DriverDAO.getInstance().find(login, password);
        Order currentOrder = OrderDAO.getInstance().find(login, UserType.DRIVER, OrderType.ACCEPTED);
        List<Order> orders = OrderDAO.getInstance().findAll(login, UserType.DRIVER);
        Location location = LocationInfoDAO.getInstance().find(login);
        model = new CurrentModel(driver, location, currentOrder, orders);
        UserDAO.getInstance().setOnline(login, true);
        UserDAO.getInstance().setRole(login, UserType.DRIVER);
        return model;
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
