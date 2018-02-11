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

/**
 * DAO abstract class which handling actions with <code>Entity</code> objects. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see DAO
 * @since JDK1.8
 */
public abstract class CommonDAO<E extends Entity> implements DAO {
    private static final Logger LOGGER = Logger.getLogger(CommonDAO.class);

    protected EntityBuilder builder;

    /**
     * Protected constructor for <code>CommonDAO</code>. Necessary for descendant.
     */
    protected CommonDAO(EntityBuilder builder) {
        this.builder = builder;
    }

    /**
     * Find the <code>Entity</code> using only login.
     *
     * @return <code>Entity</code> object.
     */
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

    /**
     * Find the <code>Entity</code> with specifying modifier.
     *
     * @return <code>Entity</code> object.
     */
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

    /**
     * Find all <code>Entity</code> objects.
     *
     * @return <code>List of Entity</code> object.
     */
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

    /**
     * Insert <code>Entity</code> object into relevant database.
     */
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

    /**
     * Update <code>Entity</code> info in relevant database.
     */
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

    /**
     * Update <code>Entity</code> status in relevant database.
     */
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

    /**
     * Delete <code>Entity</code> object from relevant database.
     */
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

}
