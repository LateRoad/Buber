package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.LocationBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>Location</code> objects. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see DAO
 * @since JDK1.8
 */
public class LocationDAO extends CommonDAO<Location> {

    private static LocationDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_SELECT_LOCATION = "SELECT * FROM `buber`.`location` AS l WHERE l.`login` = ? ";

    private static final String SQL_SELECT_ALL_LOCATIONS = "SELECT * FROM `buber`.`location`;";

    private static final String SQL_INSERT_LOCATION =
            "INSERT INTO `buber`.`location` (`login`, `lat`, `lng`) " +
                    "VALUES (?, ?, ?); ";

    private static final String SQL_DELETE_LOCATION =
            "DELETE FROM `buber`.`location` WHERE `location`.`login` = ?;";

    private static final String SQL_UPDATE_LOCATION =
            "UPDATE `buber`.`location` " +
                    "SET `lat` = ?, `lng` = ? " +
                    "WHERE `location`.`login` = ?;";


    /**
     * Private constructor for <code>LocationDAO</code>.
     */
    private LocationDAO() {
        super(new LocationBuilder());
    }

    /**
     * Returns a thread-safe singleton of the LocationDAO.
     *
     * @return LocationDAO instance.
     */
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

    /**
     * Find the <code>Location</code> using only login.
     *
     * @return <code>Location</code> object.
     */
    public Location find(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_LOCATION);
    }

    /**
     * Find all <code>Location</code> objects.
     *
     * @return <code>List of Location</code> object.
     */
    public List<Location> findAll() throws BuberSQLException, BuberLogicException {
        return super.findAll(SQL_SELECT_ALL_LOCATIONS);
    }

    /**
     * Insert <code>Location</code> object into relevant database.
     */
    public void insert(String login, Location location) throws BuberSQLException, BuberLogicException {
        super.insert(login, location, SQL_INSERT_LOCATION);
    }

    /**
     * Update <code>Location</code> info in relevant database.
     */
    public void update(String login, Location location) throws BuberSQLException, BuberLogicException {
        super.update(login, location, SQL_UPDATE_LOCATION);
    }

    /**
     * Delete <code>Location</code> object from relevant database.
     */
    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_LOCATION);
    }


}
