package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.CommonUserBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class UserDAO extends CommonDAO<CommonUser> implements DAO {

    private static final String SQL_UPDATE_MUTED =
            "UPDATE `buber`.`user` " +
                    "SET user.is_muted = ?" +
                    "WHERE user.login = ?";

    private static final String SQL_UPDATE_ONLINE =
            "UPDATE user " +
                    "SET user.is_online = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_UPDATE_ROLE =
            "UPDATE user " +
                    "SET user.role = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_SELECT_USER_IF_ONLINE =
            "SELECT * FROM user WHERE user.login = ? AND user.is_online = 1;";

    private static final String SQL_SELECT_USER =
            "SELECT * FROM user WHERE user.login = ?";


    private static UserDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static UserDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new UserDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    private UserDAO() {
        super(new CommonUserBuilder());
    }

    public CommonUser find(String login, String password) throws BuberSQLException {
        return super.find(login, password, SQL_SELECT_USER);
    }

    public void setRole(String login, UserType role) throws BuberSQLException {
        Connection connection = dbPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
            statement.setString(1, role.name());
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        } finally {
            dbPool.putConnection(connection);
        }
    }

    public void setOnline(String login, boolean isOnline) throws BuberSQLException {
        super.update(login, isOnline, SQL_UPDATE_ONLINE);
    }

    public void setMuted(String login, boolean isMuted) throws BuberSQLException {
        super.update(login, isMuted, SQL_UPDATE_MUTED);
    }

    public boolean isOnline(String login) throws BuberSQLException {
        return super.find(login, SQL_SELECT_USER_IF_ONLINE) != null;
    }
}
