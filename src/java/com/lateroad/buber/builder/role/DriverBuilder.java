package com.lateroad.buber.builder.role;

import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@code DriverBuilder} is the builder for driver entity.
 * This class allow you building driver entity from <code>ResultSet</code>,
 * <code>HttpServletRequest</code> and making necessarily statements for
 * inserting and updating information in database.
 *
 * @author LateRoad
 * @see RoleBuilder
 * @since JDK1.8
 */
public class DriverBuilder implements RoleBuilder<Driver> {
    private static final Logger LOGGER = Logger.getLogger(DriverBuilder.class);

    /**
     * Returns a Driver object made from <code>ResultSet</code>. Can throw
     * <code>BuberSQLException</code> if <code>resultSet.getString()</code> get wrong
     * string as param.
     *
     * @param resultSet from statement.
     * @return <code>Driver</code> object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     * @see PreparedStatement
     */
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

    /**
     * Returns a <code>Driver</code> object made from <code>HttpServletRequest</code>.
     * params for <code>Driver</code> is getting from constant from constants.properties.
     *
     * @param req that represent a request for java server.
     * @return <code>Driver</code> object.
     * @see HttpServletRequest
     */
    public Driver build(HttpServletRequest req) {
        return new Driver(
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("lastname"),
                req.getParameter("email"),
                req.getParameter("phone_number"),
                req.getParameter("trips_number") == null ? 0 : Integer.parseInt(req.getParameter("trips_number")),
                req.getParameter("reputation") == null ? 0 : Integer.parseInt(req.getParameter("reputation")),
                req.getParameter("is_muted") == null ? false : Boolean.parseBoolean(req.getParameter("is_muted")),
                req.getParameter("car_number"));
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
    public void makeUpdateStatement(String login, Driver driver, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, driver.getCarNumber());
            statement.setInt(2, driver.getReputation());
            statement.setString(3, driver.getPhoneNumber());
            statement.setInt(4, driver.getTripsNumber());
            statement.setString(5, login);
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an update statement for driver.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }
}
