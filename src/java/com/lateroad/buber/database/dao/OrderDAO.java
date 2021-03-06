package com.lateroad.buber.database.dao;

import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>Order</code> objects. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see DAO
 * @since JDK1.8
 */
public class OrderDAO implements DAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    private static OrderDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_SELECT_ORDER_BY_DATE = "SELECT * FROM `buber`.`order` AS o WHERE o.`date` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM `buber`.`order` ";

    private static final String SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN = "SELECT * FROM `buber`.`order` AS o WHERE o.`client_login` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN = "SELECT * FROM `buber`.`order` AS o WHERE o.`driver_login` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN_AND_TYPE = "SELECT * FROM `buber`.`order` AS o WHERE o.`client_login` = ? AND o.`status` = ?";

    private static final String SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN_AND_TYPE = "SELECT * FROM `buber`.`order` AS o WHERE o.`driver_login` = ? AND o.`status` = ?";

    private static final String SQL_INSERT_ORDER =
            "INSERT INTO `buber`.`order` (`client_login`, `driver_login`, `money`, `date`) VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_ORDER = "DELETE FROM `buber`.`order` WHERE `order`.`id` = ?; ";

    private static final String SQL_UPDATE_ORDER_INFO =
            "UPDATE `buber`.`order` " +
                    "SET `client_login` = ?, `driver_login` = ?, `money` = ?, `date` = ? " +
                    "WHERE `order`.`login` = ? ";

    private static final String SQL_UPDATE_ORDER_STATUS =
            "UPDATE `buber`.`order` " +
                    "SET `status` = ? " +
                    "WHERE `order`.`id` = ? ";

    private static final String SQL_UPDATE_ORDER_STATUS_AND_DRIVER =
            "UPDATE `buber`.`order` " +
                    "SET `driver_login` = ? " +
                    "WHERE `order`.`id` = ?";


    /**
     * Private constructor for <code>OrderDAO</code>.
     */
    private OrderDAO() {
    }

    /**
     * Returns a thread-safe singleton of the OrderDAO.
     *
     * @return OrderDAO instance.
     */
    public static OrderDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new OrderDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Find the <code>Order</code> using user login, user role and order status.
     *
     * @return <code>Order</code> object.
     */
    public Order find(String login, UserType role, OrderType status) throws BuberSQLException, BuberLogicException {
        Order order = null;
        Connection connection = connectionPool.getConnection();
        if (UserType.CLIENT.equals(role)) {
            order = findByRoleAndType(login, connection, SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN_AND_TYPE, status);
        } else if (UserType.DRIVER.equals(role)) {
            order = findByRoleAndType(login, connection, SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN_AND_TYPE, status);
        }
        return order;
    }

    /**
     * Find all <code>Order</code> objects.
     *
     * @return <code>List of Order</code> object.
     */
    public List<Order> findAll() throws BuberSQLException, BuberLogicException {
        List<Order> orders = new ArrayList<>();
        Connection cn = connectionPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_ORDERS)) {
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(cn);
        }
        return orders;
    }

    /**
     * Find all <code>Order</code> objects according to specified user login and role.
     *
     * @return <code>List of Order</code> object.
     */
    public List<Order> findAll(String login, UserType role) throws BuberSQLException, BuberLogicException {
        List<Order> orders = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        if (UserType.CLIENT.equals(role)) {
            orders = findAllByRole(login, connection, SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN);
        } else if (UserType.DRIVER.equals(role)) {
            orders = findAllByRole(login, connection, SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN);
        }
        return orders;
    }

    /**
     * Find all <code>Order</code> objects according to specified user login, user role and
     * order status.
     *
     * @return <code>List of Order</code> object.
     */
    public List<Order> findAll(String login, UserType role, OrderType status) throws BuberSQLException, BuberLogicException {
        List<Order> orders = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        if (UserType.CLIENT.equals(role)) {
            orders = findAllByRoleAndType(login, connection, SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN_AND_TYPE, status);
        } else if (UserType.DRIVER.equals(role)) {
            orders = findAllByRoleAndType(login, connection, SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN_AND_TYPE, status);
        }
        return orders;
    }

    /**
     * Insert <code>Order</code> object into order database.
     */
    public void insert(Order order) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_ORDER)) {
            st.setString(1, order.getClientLogin());
            st.setString(2, order.getDriverLogin());
            st.setBigDecimal(3, new BigDecimal(order.getMoney()));
            st.setDate(4, order.getDate());
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Update <code>Order</code> info in order database.
     */
    public void update(Order order) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_ORDER_INFO)) {
            st.setString(1, order.getStatus().name());
            st.setInt(2, order.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Update <code>Order</code> status in order database.
     */
    public void update(int orderID, OrderType status) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS)) {
            st.setString(1, status.name());
            st.setInt(2, orderID);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Update <code>Order</code> driverLogin in order database.
     */
    public void update(String driverLogin, int orderID) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS_AND_DRIVER)) {
            st.setString(1, driverLogin);
            st.setInt(2, orderID);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Delete <code>Order</code> object from order database.
     */
    public void delete(Order order) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            st.setInt(1, order.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Delete <code>Order</code> objects from order database.
     */
    public void delete(int... orders) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            for (int orderId : orders) {
                st.setInt(1, orderId);
                st.addBatch();
            }
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Delete <code>Order</code> objects from order database.
     */
    public void delete(Order... orders) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            for (Order order : orders) {
                st.setInt(1, order.getId());
                st.addBatch();
            }
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Delete <code>Order</code> object from order database by id.
     */
    public void delete(int id) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }


    private List<Order> findAllByRoleAndType(String login, Connection connection, String query, OrderType status) throws BuberSQLException, BuberLogicException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.setString(2, status.name());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
        return orders;
    }

    private Order findByRoleAndType(String login, Connection connection, String query, OrderType status) throws BuberSQLException, BuberLogicException {
        Order order = null;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.setString(2, status.name());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    order = createOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
        return order;
    }


    private List<Order> findAllByRole(String login, Connection connection, String query) throws BuberSQLException, BuberLogicException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
        return orders;
    }


    /**
     * Creates <code>Order</code> object from ResultSet.
     */
    private Order createOrder(ResultSet resultSet) throws BuberSQLException {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            order.setClientLogin(resultSet.getString("client_login"));
            order.setDriverLogin(resultSet.getString("driver_login"));
            order.setMoney(resultSet.getString("money"));
            order.setStatus(OrderType.valueOf(resultSet.getString("status").toUpperCase(Locale.ENGLISH)));
            order.setDate(resultSet.getDate("date"));
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while creating order.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return order;
    }
}




