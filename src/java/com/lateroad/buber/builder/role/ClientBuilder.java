package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.StatementBuilder;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements StatementBuilder<Client> {

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
                    resultSet.getInt("reputation"),
                    resultSet.getBoolean("is_muted"));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return client;
    }


    @Override
    public void makeSecurityInsertStatement(String login, String password, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, client.getRole().name());
            statement.setString(4, login);
            statement.setString(5, client.getName());
            statement.setString(6, client.getSurname());
            statement.setString(7, client.getLastname());
            statement.setString(8, client.getEmail());
            statement.setString(9, login);
            statement.setString(10, client.getPhoneNumber());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }

    @Override
    public void makeInsertStatement(String login, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, client.getPhoneNumber());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }

    @Override
    public void makeUpdateStatement(String login, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, client.getPhoneNumber());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }
}
