package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.builder.role.DriverBuilder;
import com.lateroad.buber.database.dao.CommonDAO;
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

public class DriverDAO extends CommonDAO<Driver> implements RoleDAO<Driver> {
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

    private static final String SQL_INSERT_DRIVER =
            "INSERT INTO buber.user (login, password, role) " +
                    "VALUES (?, ?, ?);  " +
                    "INSERT INTO buber.user_info (login, name, surname, lastname, email) " +
                    "VALUES (?, ?, ?, ?, ?); " +
                    "INSERT INTO buber.driver_info (login, phone_number, car_number) " +
                    "VALUES (?, ?, ?)";

    private static final String SQL_INSERT_DRIVER_INFO =
            "INSERT INTO buber.driver_info (login, phone_number, car_number) " +
                    "VALUES (?, ?, ?)";

    private static final String SQL_DELETE_DRIVER =
            "DELETE FROM `buber`.`driver_info` WHERE `driver_info`.`login` = ?; ";

    private static final String SQL_UPDATE_DRIVER_INFO_AND_STATUS =
            "UPDATE `buber`.`driver_info` " +
                    "SET `car_number` = ?, `reputation` = ?, `phone_number` = ?, `trips_number` = ?, `driver_license` = ?, `is_busy` = ? " +
                    "WHERE `driver_info`.`login` = ?;";

    private static final String SQL_UPDATE_DRIVER_INFO =
            "UPDATE `buber`.`driver_info` " +
                    "SET `car_number` = ?, `reputation` = ?, `phone_number` = ?, `trips_number` = ?, `driver_license` = ? " +
                    "WHERE `driver_info`.`login` = ?;";


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
        super(new DriverBuilder());
    }

    public Driver find(String login, String password) throws BuberSQLException, BuberLogicException {
        return super.find(login, password, SQL_SELECT_DRIVER);
    }

    public Driver find(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_DRIVER);
    }

    public List<Driver> findAll() throws BuberSQLException, BuberLogicException {
        return super.findAll(SQL_SELECT_ALL_DRIVERS);
    }

    public void insert(String login, Driver driver) throws BuberSQLException, BuberLogicException {
        super.insert(login, driver, SQL_INSERT_DRIVER_INFO);
    }

    public void insert(String login, String password, Driver driver) throws BuberSQLException, BuberLogicException {
        super.insert(login, password, driver, SQL_INSERT_DRIVER);
    }

    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_DRIVER);
    }

    public void update(String login, Driver driver) throws BuberSQLException, BuberLogicException {
        super.update(login, driver, SQL_UPDATE_DRIVER_INFO);
    }

    public void update(String login, boolean isBusy) throws BuberSQLException, BuberLogicException {
        super.update(login, isBusy, SQL_UPDATE_DRIVER_INFO_AND_STATUS);
    }

    public Driver find(String login, boolean isBusy) throws BuberSQLException, BuberLogicException {
        return super.find(login, isBusy, SQL_SELECT_DRIVER_WITH_STATUS);
    }

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
}
