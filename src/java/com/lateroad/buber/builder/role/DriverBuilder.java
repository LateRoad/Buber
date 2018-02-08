package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.StatementBuilder;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder implements StatementBuilder<Driver> {

    @Override
    public Driver build(ResultSet resultSet) throws BuberSQLException {
        Driver driver;
        try {
            driver = new Driver(
                    resultSet.getString("login"),
                    UserType.DRIVER,
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
            throw new BuberSQLException("Something went wrong.", e);
        }
        return driver;
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
            throw new BuberSQLException("Something went wrong.", e);
        }
    }

    @Override
    public void makeInsertStatement(String login, Driver driver, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getCarNumber());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }

    @Override
    public void makeUpdateStatement(String login, Driver driver, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getCarNumber());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }
}
