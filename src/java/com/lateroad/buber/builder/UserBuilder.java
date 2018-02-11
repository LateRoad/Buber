package com.lateroad.buber.builder;

import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@code UserBuilder} is the builder for User entity.
 * This class allow you building User entity from <code>ResultSet</code>,
 * <code>HttpServletRequest</code> and making necessarily statements for
 * inserting and updating information in database.
 *
 * @author LateRoad
 * @see EntityBuilder
 * @since JDK1.8
 */
public class UserBuilder implements EntityBuilder<User> {
    private static final Logger LOGGER = Logger.getLogger(UserBuilder.class);

    /**
     * Returns a User object made from <code>ResultSet</code>. Can throw
     * <code>BuberSQLException</code> if <code>resultSet.getString()</code> get wrong
     * string as param.
     *
     * @param resultSet from statement.
     * @return <code>User</code> object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     * @see PreparedStatement
     */
    @Override
    public User build(ResultSet resultSet) throws BuberSQLException {
        User user;
        try {
            user = new User(
                    resultSet.getString("login"),
                    UserType.valueOf(resultSet.getString("role").toUpperCase()),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"));
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a driver entity.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return user;
    }

    /**
     * Is not supported yet.
     *
     * @param req that represent a request for java server.
     * @return <code>Driver</code> object.
     * @see HttpServletRequest
     */
    @Override
    public User build(HttpServletRequest req) {
        throw new BuberUnsupportedOperationException();
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
    public void makeInsertStatement(String login, User entity, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getLastname());
            statement.setString(5, entity.getEmail());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an insert statement for user.", e);
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
    public void makeUpdateStatement(String login, User user, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getEmail());
            statement.setString(5, login);
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an update statement for driver.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }
}
