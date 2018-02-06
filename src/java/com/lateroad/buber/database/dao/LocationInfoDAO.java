package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.LocationBuilder;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LocationInfoDAO extends CommonDAO<Location> {
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


    private static LocationInfoDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static LocationInfoDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new LocationInfoDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private LocationInfoDAO() {
        super(new LocationBuilder());
    }


    public Location find(String login) throws BuberSQLException {
        return super.find(login, SQL_SELECT_LOCATION);
    }


    public List<Location> findAll() throws BuberSQLException {
        return super.findAll(SQL_SELECT_ALL_LOCATIONS);
    }


    public void insert(String login, Location location) throws BuberSQLException {
        super.insert(login, location, SQL_INSERT_LOCATION);
    }


    public void delete(String login) throws BuberSQLException {
        super.delete(login, SQL_DELETE_LOCATION);
    }


    public void update(String login, Location location) throws BuberSQLException {
        super.update(login, location, SQL_UPDATE_LOCATION);
    }
}
