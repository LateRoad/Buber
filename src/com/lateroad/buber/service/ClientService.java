package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.ClientDAO;
import com.lateroad.buber.entity.User;

import java.sql.SQLException;

public class ClientService extends UserService {
    ClientDAO clientDAO = ClientDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return clientDAO.find(login, password);
    }
}
