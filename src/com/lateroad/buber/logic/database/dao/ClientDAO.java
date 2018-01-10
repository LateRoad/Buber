package com.lateroad.buber.logic.database.dao;

import com.lateroad.buber.logic.database.CommonDAO;
import com.lateroad.buber.logic.database.DBPool;
import com.lateroad.buber.logic.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ClientDAO implements CommonDAO<User> {
    private static final String SQL_SELECT_CLIENT = "  SELECT * FROM `buber`.`user` AS u " +
            "  JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`)" +
            "  JOIN `buber`.`client_info` AS ci ON (u.`login` = ci.`login`)" +
            "  WHERE u.`login` = ?";

    private static final String SQL_SELECT_ALL_CLIENTS =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "JOIN `buber`.`client_info` AS ci ON (u.`login` = ci.`login`) ";

    private static final String SQL_INSERT_CLIENT_INFO =
            "INSERT INTO `buber`.`client_info` (`login`, `phone_number`) " +
                    "VALUES (?, ?); ";

    private static final String SQL_DELETE_CLIENT =
            "DELETE FROM `buber`.`user` WHERE `user`.`login` = ?; ";

    private static final String SQL_UPDATE_CLIENT_INFO_AND_STATUS =
            "UPDATE `buber`.`client_info` " +
                    "SET `trips_number` = ?, `reputation` = ?, `phone_number` = ?, `is_wait` = ? " +
                    "WHERE `user`.`login` = ?;";

    private static final String SQL_UPDATE_CLIENT_INFO =
            "UPDATE `buber`.`client_info` " +
                    "SET `trips_number` = ?, `reputation` = ?, `phone_number` = ? " +
                    "WHERE `user`.`login` = ?;";

    private DBPool dbPool = DBPool.getInstance();


    private static ClientDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static ClientDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ClientDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ClientDAO() {
    }

    @Override
    public User find(User user) throws SQLException {
        DBPool dbPool = DBPool.getInstance();
        User newUser = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_CLIENT)) {
            st.setString(1, user.getLogin());
            try (ResultSet resultSet = st.executeQuery();) {
                while (resultSet.next()) {
                    newUser = createClient(resultSet);
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newUser;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        DBPool dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_CLIENTS)) {
            while (resultSet.next()) {
                users.add(createClient(resultSet));
            }
        } finally {
            dbPool.putConnection(cn);
        }
        return users;
    }

    @Override
    public void insert(User entity) throws SQLException {
        DBPool dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_CLIENT_INFO)) {
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getUserInfo().getClientInfo().getPhoneNumber());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(User entity) throws SQLException {
        DBPool dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_CLIENT)) {
            st.setString(1, entity.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User entity) throws SQLException {
        DBPool dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_CLIENT_INFO)) {
            st.setInt(1, entity.getUserInfo().getClientInfo().getTripsNumber());
            st.setInt(2, entity.getUserInfo().getClientInfo().getReputation());
            st.setString(3, entity.getUserInfo().getClientInfo().getPhoneNumber());
            st.setString(4, entity.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }


    public void update(User entity, boolean isWait) throws SQLException {
        DBPool dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_CLIENT_INFO_AND_STATUS)) {
            st.setInt(1, entity.getUserInfo().getClientInfo().getTripsNumber());
            st.setInt(2, entity.getUserInfo().getClientInfo().getReputation());
            st.setString(3, entity.getUserInfo().getClientInfo().getPhoneNumber());
            st.setBoolean(4, isWait);
            st.setString(5, entity.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }


    private User createClient(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setRole(resultSet.getString("role"));
        user.getUserInfo().setName(resultSet.getString("name"));
        user.getUserInfo().setSurname(resultSet.getString("surname"));
        user.getUserInfo().setLastname(resultSet.getString("lastname"));
        user.getUserInfo().setEmail(resultSet.getString("email"));
        user.getUserInfo().getDriverInfo().setReputation(resultSet.getInt("reputation"));
        user.getUserInfo().getDriverInfo().setPhoneNumber(resultSet.getString("phone_number"));
        user.getUserInfo().getDriverInfo().setTripsNumber(resultSet.getInt("trips_number"));
        return user;
    }
}
