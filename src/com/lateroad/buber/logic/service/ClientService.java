package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.database.dao.ClientDAO;
import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;

public class ClientService implements CommonService {
    ClientDAO clientDAO = ClientDAO.getInstance();

    public User userGetByEmailAndPassword(String login, String password) throws SQLException {
        return clientDAO.find(login, password);
    }
}
