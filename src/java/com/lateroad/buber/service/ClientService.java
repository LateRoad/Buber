package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.ClientDAO;
import com.lateroad.buber.database.dao.DriverDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.OrderType;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.exception.BuberLogicException;

import java.sql.SQLException;

public class ClientService extends UserService {
    private ClientDAO clientDAO = ClientDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException, BuberLogicException {
        User client = clientDAO.find(login, password);
        setOnline(client.getLogin(), true);
        return client;
    }

    public boolean takeTaxi(String login, String driverLogin, String money) {
        boolean success = false;
        try {
            User driver = DriverDAO.getInstance().find(driverLogin);
            if (!driver.getUserInfo().getDriverInfo().isBusy()) {
                OrderDAO.getInstance().insert(new Order(login, driverLogin, money, OrderType.UNDONE));
                DriverDAO.getInstance().update(driver, true);
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
