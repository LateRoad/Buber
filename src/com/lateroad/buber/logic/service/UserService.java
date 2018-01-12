package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.OrderDAO;
import com.lateroad.buber.logic.database.dao.UserDAO;
import com.lateroad.buber.logic.entity.Order;
import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public User userGetByEmailAndPassword(String login, String password) throws SQLException
    {
        return UserDAO.getInstance().find(login, password);
    }

    public List<Order> findTrips(User user) throws SQLException {
        return  OrderDAO.getInstance().find(user);
    }
}
