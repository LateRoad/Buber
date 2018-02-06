package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.StatementBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonDAO<E extends Entity> implements DAO {

    protected StatementBuilder builder;

    protected CommonDAO(StatementBuilder builder) {
        this.builder = builder;
    }

    protected E find(String login, String query) throws BuberSQLException {
        E info = null;
        Connection connection = dbPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    info = (E) builder.build(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
        return info;
    }

    protected E find(String login, boolean modifier, String query) throws BuberSQLException {
        E info = null;
        Connection connection = dbPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.setBoolean(2, modifier);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    info = (E) builder.build(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
        return info;
    }

    protected List<E> findAll(String query) throws BuberSQLException {
        List<E> list = new ArrayList<>();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(query)) {
            while (resultSet.next()) {
                list.add((E) builder.build(resultSet));
            }
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(cn);
        }
        return list;
    }

    protected void delete(String login, String query) throws BuberSQLException {
        Connection connection = dbPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
    }

    protected void insert(String login, E entity, String query) throws BuberSQLException {
        Connection connection = dbPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            builder.makeInsertStatement(login, entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
    }

    protected void update(String login, E entity, String query) throws BuberSQLException {
        Connection connection = dbPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            builder.makeUpdateStatement(login, entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
    }

    protected E find(String login, String password, String query) throws BuberSQLException {
        E info = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt("is_online") == 0) {
                        if (resultSet.getString("password").equals(password)) {
                            info = (E) builder.build(resultSet);
                        } else {
                            throw new BuberSQLException("Wrong login or password.");
                        }
                    } else {
                        throw new BuberSQLException("User is already online.");
                    }
                }
            } finally {
                dbPool.putConnection(connection);
            }
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return info;
    }

    protected void update(String login, boolean modifier, String query) throws BuberSQLException {
        Connection connection = dbPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, modifier);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
    }
}
