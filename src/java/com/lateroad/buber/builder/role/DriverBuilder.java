package com.lateroad.buber.builder.role;

import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder implements RoleBuilder<Driver> {
    private static final Logger LOGGER = Logger.getLogger(DriverBuilder.class);


    @Override
    public Driver build(ResultSet resultSet) throws BuberSQLException {
        Driver driver;
        try {
            driver = new Driver(
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getInt("trips_number"),
                    resultSet.getInt("reputation"),
                    resultSet.getBoolean("is_muted"),
                    resultSet.getString("car_number"));
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a driver entity.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return driver;
    }


    public Driver build(HttpServletRequest req) {
        return new Driver(
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("lastname"),
                req.getParameter("email"),
                req.getParameter("phone_number"),
                Integer.parseInt(req.getParameter("trips_number")),
                Integer.parseInt(req.getParameter("reputation")),
                Boolean.parseBoolean(req.getParameter("is_muted")),
                req.getParameter("car_number"));
    }

    @Override
    public void makeSecurityInsertStatement(String login, String password, Driver driver, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, driver.getRole().name());
            statement.setString(4, login);
            statement.setString(5, driver.getName());
            statement.setString(6, driver.getSurname());
            statement.setString(7, driver.getLastname());
            statement.setString(8, driver.getEmail());
            statement.setString(9, login);
            statement.setString(10, driver.getPhoneNumber());
            statement.setString(11, driver.getCarNumber());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a security insert statement for driver.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    @Override
    public void makeInsertStatement(String login, Driver driver, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getCarNumber());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an insert statement for driver.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    @Override
    public void makeUpdateStatement(String login, Driver driver, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getCarNumber());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an update statement for driver.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }
}
