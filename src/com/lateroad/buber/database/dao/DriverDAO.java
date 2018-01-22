package com.lateroad.buber.database.dao;

import com.lateroad.buber.database.CommonDAO;
import com.lateroad.buber.database.DBPool;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.map.Geodecoder;
import org.json.JSONException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DriverDAO implements CommonDAO<User> {
    private static final String SQL_SELECT_NEAREST_DRIVERS = "{CALL `buber`.findDriversInRadius(?, ?)}";

    private static final String SQL_SELECT_DRIVER =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "JOIN `buber`.`driver_info` AS di ON (u.`login` = di.`login`) " +
                    "WHERE u.`login` = ? ";

    private static final String SQL_SELECT_ALL_DRIVERS =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "JOIN `buber`.`driver_info` AS di ON (u.`login` = di.`login`) ";

    private static final String SQL_INSERT_DRIVER_INFO =
            "INSERT INTO `buber`.`driver_info` (`login`, `car_number`, `phone_number`, `driver_license`) " +
                    "VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_DRIVER =
            "DELETE FROM `buber`.`user` WHERE `user`.`login` = ?; ";

    private static final String SQL_UPDATE_DRIVER_INFO_AND_STATUS =
            "UPDATE `buber`.`driver_info` " +
                    "SET `car_number` = ?, `reputation` = ?, `phone_number` = ?, `trips_number` = ?, `driver_license` = ?, `is_busy` = ? " +
                    "WHERE `user`.`login` = ?;";

    private static final String SQL_UPDATE_DRIVER_INFO =
            "UPDATE `buber`.`driver_info` " +
                    "SET `car_number` = ?, `reputation` = ?, `phone_number` = ?, `trips_number` = ?, `driver_license` = ? " +
                    "WHERE `user`.`login` = ?;";

    private DBPool dbPool = DBPool.getInstance();


    private static DriverDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static DriverDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DriverDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private DriverDAO() {
    }

    @Override
    public User find(User user) throws SQLException, IOException, JSONException {
        dbPool = DBPool.getInstance();
        User newUser = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_DRIVER)) {
            st.setString(1, user.getLogin());
            try (ResultSet resultSet = st.executeQuery();) {
                while (resultSet.next()) {
                    newUser = createDriver(resultSet);
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newUser;
    }

    public User find(String login, String password) throws SQLException, IOException, JSONException {
        dbPool = DBPool.getInstance();
        User newUser = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_DRIVER)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(password)) {
                        newUser = createDriver(resultSet);
                    }
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newUser;
    }

    public List<User> find(String login, int radius) throws SQLException {
        List<User> users = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (CallableStatement st = connection.prepareCall(SQL_SELECT_NEAREST_DRIVERS)) {
            st.setString(1, login);
            st.setInt(2, radius);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createDriver(resultSet));
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> findAll() throws SQLException, IOException, JSONException {
        List<User> users = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_DRIVERS)) {
            while (resultSet.next()) {
                users.add(createDriver(resultSet));
            }
        } finally {
            dbPool.putConnection(cn);
        }
        return users;
    }

    @Override
    public void insert(User entity) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_DRIVER_INFO)) {
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getUserInfo().getDriverInfo().getCarNumber());
            st.setString(3, entity.getUserInfo().getDriverInfo().getPhoneNumber());
            st.setString(4, entity.getUserInfo().getDriverInfo().getDriverLicense());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(User entity) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_DRIVER)) {
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
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_DRIVER_INFO)) {
            st.setString(1, entity.getUserInfo().getDriverInfo().getCarNumber());
            st.setInt(2, entity.getUserInfo().getDriverInfo().getReputation());
            st.setString(3, entity.getUserInfo().getDriverInfo().getPhoneNumber());
            st.setInt(4, entity.getUserInfo().getDriverInfo().getTripsNumber());
            st.setString(5, entity.getUserInfo().getDriverInfo().getPhoneNumber());
            st.setString(6, entity.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    public void update(User entity, boolean isBusy) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_DRIVER_INFO_AND_STATUS)) {
            st.setString(1, entity.getUserInfo().getDriverInfo().getCarNumber());
            st.setInt(2, entity.getUserInfo().getDriverInfo().getReputation());
            st.setString(3, entity.getUserInfo().getDriverInfo().getPhoneNumber());
            st.setInt(4, entity.getUserInfo().getDriverInfo().getTripsNumber());
            st.setString(5, entity.getUserInfo().getDriverInfo().getPhoneNumber());
            st.setBoolean(6, isBusy);
            st.setString(7, entity.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }


    private User createDriver(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setRole("driver");
        user.getUserInfo().setName(resultSet.getString("name"));
        user.getUserInfo().setSurname(resultSet.getString("surname"));
        user.getUserInfo().setLastname(resultSet.getString("lastname"));
        user.getUserInfo().setEmail(resultSet.getString("email"));
        user.getUserInfo().getDriverInfo().setCarNumber(resultSet.getString("car_number"));
        user.getUserInfo().getDriverInfo().setReputation(resultSet.getInt("reputation"));
        user.getUserInfo().getDriverInfo().setPhoneNumber(resultSet.getString("phone_number"));
        user.getUserInfo().getDriverInfo().setTripsNumber(resultSet.getInt("trips_number"));
        user.getUserInfo().getDriverInfo().setDriverLicense(resultSet.getString("driver_license"));
        return user;
    }
}
