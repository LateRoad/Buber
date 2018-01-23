package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.DriverDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.User;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class UserService {

    public User userGetByEmailAndPassword(String login, String password) throws SQLException{
        return UserDAO.getInstance().find(login, password);
    }

    public List<Order> findTrips(User user) throws SQLException {
        return OrderDAO.getInstance().find(user);
    }
}