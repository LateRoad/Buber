package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.ClientDAO;
import com.lateroad.buber.entity.User;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class ClientService extends UserService {
    private ClientDAO clientDAO = ClientDAO.getInstance();

    @Override
    public User userGetByEmailAndPassword(String login, String password) throws SQLException, IOException, JSONException {
        return clientDAO.find(login, password);
    }
}
