package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.LocationDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

public class UserService {
    public void setLocation(String login, String lat, String lng) throws BuberSQLException, BuberLogicException {
        LocationDAO.getInstance().update(login, new Location(login, lat, lng));
    }

    public void setOrderStatus(int orderID, OrderType status) throws BuberSQLException, BuberLogicException {
        OrderDAO.getInstance().update(orderID, status);
    }
}
