package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.StatementBuilder;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements StatementBuilder<Client> {
    private static final Logger LOGGER = Logger.getLogger(ClientBuilder.class);


    @Override
    public Client build(ResultSet resultSet) throws BuberSQLException {
        Client client;
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
            LOGGER.error("SQLException was occurred building a client entity.", e);
            throw new BuberSQLException("Something went wrong.");
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
            LOGGER.error("SQLException was occurred building a security insert statement for client.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    @Override
    public void makeInsertStatement(String login, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, client.getPhoneNumber());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an insert statement for client.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    @Override
    public void makeUpdateStatement(String login, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, client.getPhoneNumber());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an update statement for client.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }
}
