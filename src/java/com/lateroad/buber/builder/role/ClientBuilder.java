package com.lateroad.buber.builder.role;

import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@code ClientBuilder} is the builder for client entity.
 * This class allow you building client entity from <code>ResultSet</code>,
 * <code>HttpServletRequest</code> and making necessarily statements for
 * inserting and updating information in database.
 *
 * @author LateRoad
 * @see RoleBuilder
 * @since JDK1.8
 */
public class ClientBuilder implements RoleBuilder<Client> {
    private static final Logger LOGGER = Logger.getLogger(ClientBuilder.class);

    /**
     * Returns a Client object made from <code>ResultSet</code>. Can throw
     * <code>BuberSQLException</code> if <code>resultSet.getString()</code> get wrong
     * string as param.
     *
     * @param resultSet from statement.
     * @return <code>Client</code> object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     * @see PreparedStatement
     */
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

    /**
     * Returns a <code>Client</code> object made from <code>HttpServletRequest</code>.
     * params for <code>Client</code> is getting from constant from constants.properties.
     *
     * @param req that represent a request for java server.
     * @return <code>Client</code> object.
     * @see HttpServletRequest
     */
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
    public void makeInsertStatement(String login, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, client.getPhoneNumber());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an insert statement for client.", e);
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
    public void makeUpdateStatement(String login, Client client, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setInt(1 , client.getTripsNumber());
            statement.setInt(2 , client.getReputation());
            statement.setString(3, client.getPhoneNumber());
            statement.setString(4, login);
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an update statement for client.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }
}
