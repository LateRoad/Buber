package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.CardDAO;
import com.lateroad.buber.database.dao.LocationDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.Card;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.List;

/**
 * Class provides common for every user operations and incapsulate using of DAO layout.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class UserService {

    /**
     * Set current location for specified user in database.
     *
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the operation was broken.
     */
    public void setLocation(String login, String lat, String lng) throws BuberSQLException, BuberLogicException {
        LocationDAO.getInstance().update(login, new Location(login, lat, lng));
    }

    /**
     * Set order status.
     *
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the operation was broken.
     */
    public void setOrderStatus(int orderID, OrderType status) throws BuberSQLException, BuberLogicException {
        OrderDAO.getInstance().update(orderID, status);
    }

    /**
     * Perform a search of orders with type UNDONE for specified user.
     *
     * @return <code>List of Orders</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the search all orders was broken.
     */
    public List<Order> findActiveOrders(String login, UserType role) throws BuberSQLException, BuberLogicException {
        return OrderDAO.getInstance().findAll(login, role, OrderType.UNDONE);
    }

    /**
     * Perform a search of orders for specified user.
     *
     * @return <code>List of Orders</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the search all orders was broken.
     */
    public List<Order> findTrips(CommonUser user) throws BuberSQLException, BuberLogicException {
        return OrderDAO.getInstance().findAll(user.getLogin(), user.getRole());
    }

    /**
     * Perform a search of orders for specified user.
     *
     * @return <code>List of Orders</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the search all orders was broken.
     */
    public List<Card> findCards(CommonUser user) throws BuberSQLException, BuberLogicException {
        return CardDAO.getInstance().findAll(user.getLogin());
    }

}
