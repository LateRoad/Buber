package com.lateroad.buber.builder.role;

import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@code CommonUser} is the builder for commonUser entity.
 * This class allow you building commonUser entity from <code>ResultSet</code>,
 * <code>HttpServletRequest</code> and making necessarily statements for
 * inserting and updating information in database.
 *
 * @author LateRoad
 * @see RoleBuilder
 * @since JDK1.8
 */
public class CommonUserBuilder implements RoleBuilder<CommonUser> {
    private static final Logger LOGGER = Logger.getLogger(CommonUserBuilder.class);

    /**
     * Returns a CommonUser object made from <code>ResultSet</code>. Can throw
     * <code>BuberSQLException</code> if <code>resultSet.getString()</code> get wrong
     * string as param.
     *
     * @param resultSet from statement.
     * @return <code>CommonUser</code> object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     * @see PreparedStatement
     */
    @Override
    public CommonUser build(ResultSet resultSet) throws BuberSQLException {
        CommonUser user;
        try {
            user = new CommonUser(
                    resultSet.getString("login"),
                    UserType.valueOf(resultSet.getString("role").toUpperCase()));
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a common user entity.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return user;
    }

    /**
     * Returns a <code>CommonUser</code> object made from <code>HttpServletRequest</code>.
     * params for <code>CommonUser</code> is getting from constant from constants.properties.
     *
     * @param req that represent a request for java server.
     * @return <code>CommonUser</code> object.
     * @see HttpServletRequest
     */
    @Override
    public CommonUser build(HttpServletRequest req) {
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

    /**
     * Filling in the insert statement using password for executing in DAO.
     * Throws a BuberSQLException if count of fields for filling in in statement is not
     * equals to count that method provides.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
    public void makeSecurityInsertStatement(String login, String password, CommonUser entity, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, entity.getRole().name());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a security insert statement for common user.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    /**
     * Is not supported yet.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
    @Override
    public void makeInsertStatement(String login, CommonUser entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperationException();
    }

    /**
     * Is not supported yet.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
    @Override
    public void makeUpdateStatement(String login, CommonUser entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperationException();
    }
}
