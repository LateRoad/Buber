package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
import com.lateroad.buber.service.CommonUserService;

import java.util.List;

public class AdminService implements CommonUserService<CurrentModel> {
    @Override
    public CurrentModel authentication(String login, String password) {
        CurrentModel model = null;
        try {
            CommonUser admin = UserDAO.getInstance().find(login, password);
            model = new CurrentModel(admin);
            UserDAO.getInstance().setOnline(login, true);
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
        return model;
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

}