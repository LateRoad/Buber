package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.List;

public class OrderService {

    public List<Order> findActiveOrders(String login, UserType role) throws BuberSQLException {
        return OrderDAO.getInstance().findAll(login, role, OrderType.UNDONE);
    }
        public List<Order> findTrips(CommonUser user) throws BuberSQLException {
        return OrderDAO.getInstance().findAll(user.getLogin(), user.getRole());
    }

}
