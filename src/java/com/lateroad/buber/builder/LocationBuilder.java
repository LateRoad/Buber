package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Location;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationBuilder implements EntityBuilder<Location> {
    private static final Logger LOGGER = Logger.getLogger(LocationBuilder.class);


    @Override
    public Location build(ResultSet resultSet) throws BuberSQLException {
        Location location;
        try {
            location = new Location(
                    resultSet.getString("login"),
                    resultSet.getString("lat"),
                    resultSet.getString("lng"));
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a location entity.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return location;
    }

    @Override
    public Location build(HttpServletRequest req) {
        return new Location(
                req.getParameter("login"),
                req.getParameter("lat"),
                req.getParameter("lng"));
    }

    @Override
    public void makeInsertStatement(String login, Location location, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, location.getLat());
            statement.setString(3, location.getLng());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an insert statement for location.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    @Override
    public void makeUpdateStatement(String login, Location location, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, location.getLat());
            statement.setString(2, location.getLng());
            statement.setString(3, login);
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an update statement for location.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }
}
