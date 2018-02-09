package com.lateroad.buber.builder.role;

import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements RoleBuilder<Client> {
    private static final Logger LOGGER = Logger.getLogger(ClientBuilder.class);


    @Override
    public Client build(ResultSet resultSet) throws BuberSQLException {
        Client client;
        try {
            client = new Client(
                    resultSet.getString("login"),
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

    public Client build(HttpServletRequest req) {
        return new Client(
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("lastname"),
                req.getParameter("email"),
                req.getParameter("phone_number"),
                req.getParameter("trips_number") == null ? 0 : Integer.parseInt(req.getParameter("trips_number")),
                req.getParameter("reputation") == null ? 0 : Integer.parseInt(req.getParameter("reputation")),
                req.getParameter("is_muted") == null ? false : Boolean.parseBoolean(req.getParameter("is_muted")));
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
