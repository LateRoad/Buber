package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.database.dao.CommonDAO;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class RoleDAO<E extends CommonUser> extends CommonDAO<E> {


    protected void insert(String login, String password, E entity, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            builder.makeSecurityInsertStatement(login, password, entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

}
