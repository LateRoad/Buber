package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.CommonUserDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import com.lateroad.buber.service.CommonUserService;

import java.util.List;

/**
 * Class provides administrator operations and incapsulate using of DAO layout.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class AdminService implements CommonUserService<CommonUser> {


    /**
     * Perform a authentication operation.
     *
     * @return <code>CommonUser</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of authentication was broken.
     */
    @Override
    public CommonUser authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        CommonUser admin = CommonUserDAO.getInstance().find(login, password);
        if (admin != null && admin.getRole().equals(UserType.ADMIN)) {
            CommonUserDAO.getInstance().setOnline(login, true);
        } else {
            throw new BuberLogicException("Wrong credentials.");
        }
        return admin;
    }

    /**
     * Is not supported yet.
     */
    @Override
    public CommonUser registration(CommonUser commonUser, String password, String confirmPassword) throws BuberSQLException {
        throw new BuberUnsupportedOperationException();
    }

    /**
     * Perform a search of all orders operation.
     *
     * @return <code>List of Orders</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the search all orders was broken.
     */
    public List<Order> findAllOrders() throws BuberSQLException, BuberLogicException {
        return OrderDAO.getInstance().findAll();
    }

    /**
     * Perform a search of all drivers operation.
     *
     * @return <code>List of Drivers</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the search all drivers was broken.
     */
    public List<Driver> findAllDrivers() throws BuberSQLException, BuberLogicException {
        return DriverDAO.getInstance().findAll();
    }

    /**
     * Perform a search of all clients operation.
     *
     * @return <code>List of Client</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the search all clients was broken.
     */
    public List<Client> findAllClients() throws BuberSQLException, BuberLogicException {
        return ClientDAO.getInstance().findAll();
    }

    /**
     * Set user muted.
     *
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the setting of user status was broken.
     */
    public void setMuted(String login, boolean status) throws BuberSQLException, BuberLogicException {
        CommonUserDAO.getInstance().setMuted(login, status);
    }

}