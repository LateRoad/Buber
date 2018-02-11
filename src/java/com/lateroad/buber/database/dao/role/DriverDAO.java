package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.builder.role.DriverBuilder;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>Driver</code> object. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see RoleDAO
 * @since JDK1.8
 */
public class DriverDAO extends RoleDAO<Driver> {
    private static final Logger LOGGER = Logger.getLogger(DriverDAO.class);

    private static DriverDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_SELECT_NEAREST_DRIVERS = "{CALL `buber`.findDriversInRadius(?, ?)}";

    private static final String SQL_SELECT_DRIVER =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "JOIN `buber`.`driver_info` AS di ON (u.`login` = di.`login`) " +
                    "WHERE u.`login` = ?;";

    private static final String SQL_SELECT_DRIVER_WITH_STATUS =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "JOIN `buber`.`driver_info` AS di ON (u.`login` = di.`login`) " +
                    "WHERE u.`login` = ? AND is_busy = ?;";

    private static final String SQL_SELECT_ALL_DRIVERS =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "JOIN `buber`.`driver_info` AS di ON (u.`login` = di.`login`);";

    private static final String SQL_INSERT_DRIVER_INFO =
            "INSERT INTO buber.driver_info (login, phone_number, car_number) " +
                    "VALUES (?, ?, ?)";

    private static final String SQL_DELETE_DRIVER =
            "DELETE FROM `buber`.`driver_info` WHERE `driver_info`.`login` = ?; ";

    private static final String SQL_UPDATE_DRIVER_INFO =
            "UPDATE `buber`.`driver_info` " +
                    "SET `car_number` = ?, `reputation` = ?, `phone_number` = ?, `trips_number` = ? " +
                    "WHERE `driver_info`.`login` = ?;";

    private static final String SQL_UPDATE_DRIVER_STATUS =
            "UPDATE `buber`.`driver_info` " +
                    "SET  `is_busy` = ? " +
                    "WHERE `driver_info`.`login` = ?;";


    /**
     * Private constructor for <code>DriverDAO</code>.
     */
    private DriverDAO() {
        super(new DriverBuilder());
    }

    /**
     * Returns a thread-safe singleton of the DriverDAO.
     *
     * @return DriverDAO instance.
     */
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


    /**
     * Find the <code>Driver</code> using login and password.
     *
     * @return <code>Driver</code> object.
     */
    public Driver find(String login, String password) throws BuberSQLException, BuberLogicException {
        return super.find(login, password, SQL_SELECT_DRIVER);
    }

    /**
     * Find the <code>Driver</code> using only login.
     *
     * @return <code>Driver</code> object.
     */
    public Driver find(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_DRIVER);
    }

    /**
     * Find the <code>Driver</code> with specifying status.
     *
     * @return <code>Driver</code> object.
     */
    public Driver find(String login, boolean isBusy) throws BuberSQLException, BuberLogicException {
        return super.find(login, isBusy, SQL_SELECT_DRIVER_WITH_STATUS);
    }

    /**
     * Find the <code>Driver</code> with specified radius. Uses procedure from database which
     * selects drivers according their online mode and latitude, longitude for specified client.
     *
     * @return <code>Driver</code> object.
     */
    public List<Driver> find(String login, int radius) throws BuberSQLException, BuberLogicException {
        List<Driver> users = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (CallableStatement st = connection.prepareCall(SQL_SELECT_NEAREST_DRIVERS)) {
            st.setString(1, login);
            st.setInt(2, radius);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    users.add((Driver) builder.build(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
        return users;
    }

    /**
     * Find all <code>Driver</code> objects.
     *
     * @return <code>List of Driver</code> object.
     */
    public List<Driver> findAll() throws BuberSQLException, BuberLogicException {
        return super.findAll(SQL_SELECT_ALL_DRIVERS);
    }

    /**
     * Insert <code>Driver</code> object into driver_info database.
     */
    public void insert(String login, Driver driver) throws BuberSQLException, BuberLogicException {
        super.insert(login, driver, SQL_INSERT_DRIVER_INFO);
    }

    /**
     * Update <code>Driver</code> info in driver_info database.
     */
    public void update(String login, Driver driver) throws BuberSQLException, BuberLogicException {
        super.update(login, driver, SQL_UPDATE_DRIVER_INFO);
    }

    /**
     * Update <code>Driver</code> status in driver_info database.
     */
    public void update(String login, boolean isBusy) throws BuberSQLException, BuberLogicException {
        super.update(login, isBusy, SQL_UPDATE_DRIVER_STATUS);
    }

    /**
     * Delete <code>Driver</code> object from driver_info database.
     */
    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_DRIVER);
    }
}
