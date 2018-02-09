package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.builder.role.RoleBuilder;
import com.lateroad.buber.database.dao.CommonDAO;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RoleDAO<E extends CommonUser> extends CommonDAO<E> {
    private static final Logger LOGGER = Logger.getLogger(RoleDAO.class);

    protected RoleDAO(RoleBuilder builder) {
        super(builder);
    }

    protected void insert(String login, String password, E entity, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ((RoleBuilder) builder).makeSecurityInsertStatement(login, password, entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    protected E find(String login, String password, String query) throws BuberSQLException, BuberLogicException {
        E info = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    if (!resultSet.getBoolean("is_online")) {
                        if (!resultSet.getBoolean("is_muted")) {
                            if (resultSet.getString("password").equals(password)) {
                                info = (E) builder.build(resultSet);
                            } else {
                                throw new BuberLogicException("Wrong login or password.");
                            }
                        } else {
                            throw new BuberLogicException("User is muted.");
                        }
                    } else {
                        throw new BuberLogicException("User is already online.");
                    }
                }
            } finally {
                connectionPool.putConnection(connection);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return info;
    }

}
