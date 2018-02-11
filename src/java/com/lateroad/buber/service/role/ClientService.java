package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.CommonUserDAO;
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

/**
 * Class provides client operations and incapsulate using of DAO layout.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class ClientService implements CommonUserService<Client> {


    /**
     * Perform a authentication operation.
     *
     * @return <code>Client</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of authentication was broken.
     */
    @Override
    public Client authentication(String login, String password) throws BuberSQLException, BuberLogicException {
        Client client = ClientDAO.getInstance().find(login, password);
        if (client != null && client.getRole().equals(UserType.CLIENT)) {
            CommonUserDAO.getInstance().setOnline(login, true);
            CommonUserDAO.getInstance().setRole(login, UserType.CLIENT);
        } else {
            throw new BuberLogicException("Wrong credentials.");
        }
        return client;
    }

    /**
     * Perform a registration operation.
     *
     * @return <code>Client</code> object.
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of registration was broken.
     */
    @Override
    public Client registration(Client client, String password, String confirmPassword) throws BuberSQLException, BuberLogicException {
        if (FormValidator.checkNecessaryFields(client)) {
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

    /**
     * Inserts order into database and set isBusy status of driver into true.
     *
     * @throws BuberSQLException   throws if something was wrong in DAO layout.
     * @throws BuberLogicException if correct way of the operation was broken.
     */
    public void takeTaxi(String login, String driverLogin, String money) throws BuberSQLException, BuberLogicException {
        User driver = DriverDAO.getInstance().find(driverLogin, false);
        if (driver == null) {
            throw new BuberLogicException("Driver is already busy.");
        }
        OrderDAO.getInstance().insert(new Order(login, driver.getLogin(), money, OrderType.UNDONE));
        DriverDAO.getInstance().update(login, true);
    }
}
