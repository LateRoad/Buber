package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.StatementBuilder;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements RoleBuilder<Client>, StatementBuilder<Client> {

    @Override
    public Client build(ResultSet resultSet) throws BuberSQLException {
        Client client = null;
        try {
            client = new Client(
                    resultSet.getString("login"),
                    UserType.CLIENT,
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getInt("trips_number"),
                    resultSet.getInt("reputation"));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return client;
    }

    @Override
    public void makeInsertStatement(String login, Client clientInfo, PreparedStatement statement) {
        try {
            statement.setString(1, login);
            statement.setString(2, clientInfo.getPhoneNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeUpdateStatement(String login, Client entity, PreparedStatement statement) {

    }
}
