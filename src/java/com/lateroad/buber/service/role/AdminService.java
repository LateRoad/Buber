package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.CommonUserDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;

import java.util.List;

public class AdminService implements CommonUserService<CommonUser> {
    @Override
    public CommonUser authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        CommonUser admin = CommonUserDAO.getInstance().find(login, password);
        CommonUserDAO.getInstance().setOnline(login, true);
        return admin;
    }

    @Override
    public CommonUser registration(String... params) throws BuberSQLException {
        throw new UnsupportedOperationException();
    }

    public List<Order> findAllOrders() throws BuberSQLException {
        return OrderDAO.getInstance().findAll();
    }

    public List<Driver> findAllDrivers() throws BuberSQLException {
        return DriverDAO.getInstance().findAll();
    }

    public List<Client> findAllClients() throws BuberSQLException {
        return ClientDAO.getInstance().findAll();
    }

    public void setMuted(String login, boolean status) throws BuberSQLException {
        CommonUserDAO.getInstance().setMuted(login, status);
    }

}