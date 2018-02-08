package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.CommonUserBuilder;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CommonUserDAO extends CommonDAO<CommonUser> {
    private static final Logger LOGGER = Logger.getLogger(CommonUserDAO.class);

    private static CommonUserDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_UPDATE_MUTED =
            "UPDATE `buber`.`user` " +
                    "SET user.is_muted = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_UPDATE_ONLINE =
            "UPDATE user " +
                    "SET user.is_online = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_INSERT_USER =
            "INSERT INTO `buber`.`user` (`login`, `password`, `role`) " +
                    "VALUES (?, ?, ?);";

    private static final String SQL_UPDATE_ROLE =
            "UPDATE user " +
                    "SET user.role = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_SELECT_USER_IF_ONLINE =
            "SELECT * FROM user WHERE user.login = ? AND user.is_online = 1;";

    private static final String SQL_SELECT_USER =
            "SELECT * FROM user WHERE user.login = ?";


    public static CommonUserDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new CommonUserDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private CommonUserDAO() {
        super(new CommonUserBuilder());
    }


    public void setMuted(String login, boolean isMuted) throws BuberSQLException, BuberLogicException {
        super.update(login, isMuted, SQL_UPDATE_MUTED);
    }

    public void setOnline(String login, boolean isOnline) throws BuberSQLException, BuberLogicException {
        super.update(login, isOnline, SQL_UPDATE_ONLINE);
    }

    public void insert(String login, String password, CommonUser user) throws BuberSQLException, BuberLogicException {
        super.insert(login, password, user, SQL_INSERT_USER);
    }

    public void insert(String login, String password, UserType role) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, role.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    public void setRole(String login, UserType role) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
            statement.setString(1, role.name());
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    public CommonUser find(String login, String password) throws BuberSQLException, BuberLogicException {
        return super.find(login, password, SQL_SELECT_USER);
    }


    public boolean isOnline(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_USER_IF_ONLINE) != null;
    }

    public boolean isExist(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_USER) != null;
    }
}
