package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.CommonUserDAO;
import com.lateroad.buber.database.dao.LocationDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;

public class ClientService implements CommonUserService<Client> {

    @Override
    public Client authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        Client client = null;
        client = ClientDAO.getInstance().find(login, password);
        CommonUserDAO.getInstance().setOnline(login, true);
        CommonUserDAO.getInstance().setRole(login, UserType.CLIENT);
        return client;
    }

    @Override
    public Client registration(String... params) throws BuberSQLException, BuberLogicException {
        Client client = null;
        if (!CommonUserDAO.getInstance().isExist(params[0])) {
            client = new Client(params[0], UserType.CLIENT, params[2], params[3], params[4], params[5], params[6]);
            CommonUserDAO.getInstance().insert(client.getLogin(), params[1], client.getRole());
            UserDAO.getInstance().insert(client.getLogin(), client);
            ClientDAO.getInstance().insert(client.getLogin(), client);
            CommonUserDAO.getInstance().setOnline(client.getLogin(), true);
        } else {
            ClientDAO.getInstance().insert(client.getLogin(), client);
        }
        return client;
    }


    public void takeTaxi(String login, String driverLogin, String money) throws BuberSQLException {
        User driver = DriverDAO.getInstance().find(driverLogin, false);
        if (driver == null) {
            throw new BuberSQLException("Водитель занят");
        }
        OrderDAO.getInstance().insert(new Order(login, driver.getLogin(), money, OrderType.UNDONE));
        DriverDAO.getInstance().update(login, true);
    }

    public void setLocation(String login, String lat, String lng) throws BuberSQLException {
        LocationDAO.getInstance().update(login, new Location(login, lat, lng));
    }
}
