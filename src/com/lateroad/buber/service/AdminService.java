package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.ClientDAO;
import com.lateroad.buber.database.dao.DriverDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.User;

import java.sql.SQLException;
import java.util.List;

public class AdminService extends UserService {
    private UserDAO userDAO = UserDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return userDAO.find(login, password, "admin");
    }

    public List<Order> findAllOrders() throws SQLException {
        return OrderDAO.getInstance().findAll();
    }

    public List<User> findAllDrivers() throws SQLException {
        return DriverDAO.getInstance().findAll();
    }

    public List<User> findAllClients() throws SQLException {
        return ClientDAO.getInstance().findAll();
    }

}