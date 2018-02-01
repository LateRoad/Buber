package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.exception.BuberLogicException;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    public User userGetByEmailAndPassword(String login, String password) throws SQLException, BuberLogicException {
        User user = UserDAO.getInstance().find(login, password, "client");
        setOnline(user.getLogin(), true);
        return user;
    }

    public List<Order> findTrips(User user) throws SQLException {
        return OrderDAO.getInstance().find(user);
    }

    public void setOnline(String login, boolean status) throws SQLException {
        UserDAO.getInstance().update(login, status);
    }
}
