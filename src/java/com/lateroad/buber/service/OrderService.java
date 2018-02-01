package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.OrderType;
import com.lateroad.buber.entity.User;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public List<Order> findActiveOrders(User user) throws SQLException {
        return OrderDAO.getInstance().find(user, OrderType.UNDONE);
    }
}
