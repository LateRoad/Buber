package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.EntityBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonDAO<E extends Entity> implements DAO {
    private static final Logger LOGGER = Logger.getLogger(CommonDAO.class);

    protected EntityBuilder builder;


    protected CommonDAO(EntityBuilder builder) {
        this.builder = builder;
    }


    protected E find(String login, String query) throws BuberSQLException, BuberLogicException {
        E info = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    info = (E) builder.build(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
        return info;
    }

    protected E find(String login, boolean modifier, String query) throws BuberSQLException, BuberLogicException {
        E info = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.setBoolean(2, modifier);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    info = (E) builder.build(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
        return info;
    }

    protected List<E> findAll(String query) throws BuberSQLException, BuberLogicException {
        List<E> list = new ArrayList<>();
        Connection cn = connectionPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(query)) {
            while (resultSet.next()) {
                list.add((E) builder.build(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(cn);
        }
        return list;
    }

    protected void delete(String login, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    protected void insert(String login, E entity, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            builder.makeInsertStatement(login, entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }


    protected void update(String login, E entity, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            builder.makeUpdateStatement(login, entity, statement);
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
                    if (resultSet.getInt("is_online") == 0) {
                        if (resultSet.getString("password").equals(password)) {
                            info = (E) builder.build(resultSet);
                        } else {
                            throw new BuberLogicException("Wrong login or password.");
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

    protected void update(String login, boolean modifier, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, modifier);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }
}
