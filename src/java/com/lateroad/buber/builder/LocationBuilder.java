package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Location;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@code LocationBuilder} is the builder for location entity.
 * This class allow you building location entity from <code>ResultSet</code>,
 * <code>HttpServletRequest</code> and making necessarily statements for
 * inserting and updating information in database.
 *
 * @author LateRoad
 * @see EntityBuilder
 * @since JDK1.8
 */
public class LocationBuilder implements EntityBuilder<Location> {
    private static final Logger LOGGER = Logger.getLogger(LocationBuilder.class);

    /**
     * Returns a Location object made from <code>ResultSet</code>. Can throw
     * <code>BuberSQLException</code> if <code>resultSet.getString()</code> get wrong
     * string as param.
     *
     * @param resultSet from statement.
     * @return <code>Client</code> object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     * @see PreparedStatement
     */
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

    /**
     * Returns a <code>Location</code> object made from <code>HttpServletRequest</code>.
     * params for <code>Location</code> is getting from constant from constants.properties.
     *
     * @param req that represent a request for java server.
     * @return <code>Location</code> object.
     * @see HttpServletRequest
     */
    @Override
    public Location build(HttpServletRequest req) {
        return new Location(
                req.getParameter("login"),
                req.getParameter("lat"),
                req.getParameter("lng"));
    }

    /**
     * Filling in the insert statement for executing in DAO. Throws a BuberSQLException if
     * count of fields for filling in in statement is not equals to count that method
     * provides.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
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

    /**
     * Filling in the update statement for executing in DAO. Throw a BuberSQLException if
     * count of fields for filling in in statement is not equals to count that method
     * provides.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
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
