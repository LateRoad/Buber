package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.StatementBuilder;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder implements RoleBuilder<Driver>, StatementBuilder<Driver> {

    @Override
    public Driver build(ResultSet resultSet) throws BuberSQLException {
        Driver driver = null;
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
                    resultSet.getString("car_number"));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return driver;
    }

    @Override
    public void makeInsertStatement(String login, Driver driver, PreparedStatement statement) {
        try {
            statement.setString(1, login);
            statement.setString(2, driver.getCarNumber());
            statement.setString(3, driver.getPhoneNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeUpdateStatement(String login, Driver driver, PreparedStatement statement) {

    }
}
