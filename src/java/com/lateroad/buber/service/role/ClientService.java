package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.CommonUserDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.validator.FormValidator;

public class ClientService implements CommonUserService<Client> {

    @Override
    public Client authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        Client client = ClientDAO.getInstance().find(login, password);
        CommonUserDAO.getInstance().setOnline(login, true);
        CommonUserDAO.getInstance().setRole(login, UserType.CLIENT);
        return client;
    }

    @Override
    public Client registration(Client client, String password, String confirmPassword) throws BuberSQLException, BuberLogicException {
        if (FormValidator.checkNessesaryFields(client)) {
            if (FormValidator.checkPasswords(password, confirmPassword)) {
                if (!CommonUserDAO.getInstance().isExist(client.getLogin())) {
                    CommonUserDAO.getInstance().insert(client.getLogin(), password, client.getRole());
                    UserDAO.getInstance().insert(client.getLogin(), client);
                    ClientDAO.getInstance().insert(client.getLogin(), client);
                    CommonUserDAO.getInstance().setOnline(client.getLogin(), true);
                } else {
                    ClientDAO.getInstance().insert(client.getLogin(), client);
                }
            } else {
                throw new BuberLogicException("Passwords are different.");
            }
        } else {
            throw new BuberLogicException("Fill in all fields.");
        }
        return client;
    }


    public void takeTaxi(String login, String driverLogin, String money) throws BuberSQLException, BuberLogicException {
        User driver = DriverDAO.getInstance().find(driverLogin, false);
        if (driver == null) {
            throw new BuberLogicException("Driver is already busy.");
        }
        OrderDAO.getInstance().insert(new Order(login, driver.getLogin(), money, OrderType.UNDONE));
        DriverDAO.getInstance().update(login, true);
    }
}
