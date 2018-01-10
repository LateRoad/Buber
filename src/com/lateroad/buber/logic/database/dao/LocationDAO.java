package com.lateroad.buber.logic.database.dao;

import com.lateroad.buber.logic.database.CommonDAO;
import com.lateroad.buber.logic.database.DBPool;
import com.lateroad.buber.logic.entity.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LocationDAO implements CommonDAO<Location> {
    private static final String SQL_SELECT_LOCATION = "SELECT * FROM `buber`.`location` AS l WHERE l.`login` = ? ";

    private static final String SQL_SELECT_ALL_LOCATIONS = "SELECT * FROM `buber`.`user` AS u ";

    private static final String SQL_INSERT_LOCATION =
            "INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) " +
                    "VALUES (?, ?, ?, ?, ?); ";

    private static final String SQL_DELETE_LOCATION =
            "DELETE FROM `buber`.`location` WHERE `location`.`login` = ?; ";

    private static final String SQL_UPDATE_LOCATION =
            "UPDATE `buber`.`location` " +
                    "SET `country` = ?, `city` = ?, `street` = ?, `house_number` = ? " +
                    "WHERE `location`.`login` = ?;";

    private DBPool dbPool = DBPool.getInstance();


    private static LocationDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static LocationDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new LocationDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private LocationDAO() {
    }

    @Override
    public Location find(Location location) throws SQLException {
        dbPool = DBPool.getInstance();
        Location newLocation = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_LOCATION)) {
            st.setString(1, location.getLogin());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    newLocation = createLocation(resultSet);
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newLocation;
    }

    @Override
    public Location find(String param1, String param2) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Location> findAll() throws SQLException {
        List<Location> locations = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_LOCATIONS)) {
            while (resultSet.next()) {
                locations.add(createLocation(resultSet));
            }
        } finally {
            dbPool.putConnection(cn);
        }
        return locations;
    }

    @Override
    public void insert(Location location) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_LOCATION)) {
            st.setString(1, location.getLogin());
            st.setString(2, location.getCountry());
            st.setString(3, location.getCity());
            st.setString(4, location.getStreet());
            st.setString(5, location.getHouseNumber());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(Location location) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_LOCATION)) {
            st.setString(1, location.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    public void delete(String login) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_LOCATION)) {
            st.setString(1, login);
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
    public void update(Location location) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_LOCATION)) {
            st.setString(1, location.getCountry());
            st.setString(2, location.getCity());
            st.setString(3, location.getStreet());
            st.setString(4, location.getHouseNumber());
            st.setString(5, location.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    private Location createLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        location.setCountry(resultSet.getString("country"));
        location.setCity(resultSet.getString("city"));
        location.setStreet(resultSet.getString("street"));
        location.setHouseNumber(resultSet.getString("house_number"));
        return location;
    }
}




