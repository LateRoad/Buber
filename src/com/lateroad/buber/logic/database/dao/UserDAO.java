package com.lateroad.buber.logic.database.dao;

import com.lateroad.buber.logic.database.DBPool;
import com.lateroad.buber.logic.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class UserDAO {
    private static final String SQL_SELECT_USER = "SELECT * FROM `buber`.`user` AS u WHERE u.`login` = ?";

    private static final String SQL_SELECT_USER_IF_ONLINE = "  SELECT * FROM `buber`.`user` AS u WHERE u.`login` = ? AND `is_online = 1`";

    private static final String SQL_UPDATE_USER =
            "UPDATE `buber`.`user` " +
                    "SET `is_online` = ?, `is_muted` = ?" +
                    "WHERE `user`.`login` = ?;";


    private DBPool dbPool = DBPool.getInstance();


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
    }

    public User find(String login, String password) throws SQLException {
        dbPool = DBPool.getInstance();
        User newUser = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_USER)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(password)) {
                        newUser = createUser(resultSet);
                    }
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newUser;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setRole(resultSet.getString("role"));
        return user;
    }


    public boolean isExist(String login) throws SQLException {
        Connection connection = dbPool.getConnection();
        return isConsist(login, connection, SQL_SELECT_USER);
    }


    public boolean isOnline(String login) throws SQLException {
        Connection connection = dbPool.getConnection();
        return isConsist(login, connection, SQL_SELECT_USER_IF_ONLINE);
    }

    public void update(String login, boolean isOnline, boolean isMuted) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_USER)) {
            st.setBoolean(1, isOnline);
            st.setBoolean(2, isMuted);
            st.setString(3, login);
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }



    private boolean isConsist(String login, Connection connection, String sqlSelectUser) throws SQLException {
        boolean isExist;
        try (PreparedStatement st = connection.prepareStatement(sqlSelectUser)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                isExist = resultSet.next();
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return isExist;
    }
}
