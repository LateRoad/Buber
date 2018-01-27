package com.lateroad.buber.database.dao;

import com.lateroad.buber.database.CommonDAO;
import com.lateroad.buber.database.DBPool;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.OrderType;
import com.lateroad.buber.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class OrderDAO implements CommonDAO<Order> {
    private static final String SQL_SELECT_ORDER_BY_DATE = "SELECT * FROM `buber`.`order` AS o WHERE o.`date` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM `buber`.`order` ";

    private static final String SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN = "SELECT * FROM `buber`.`order` AS o WHERE o.`client_login` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN = "SELECT * FROM `buber`.`order` AS o WHERE o.`driver_login` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN_AND_TYPE = "SELECT * FROM `buber`.`order` AS o WHERE o.`client_login` = ? AND o.`status` = ?";

    private static final String SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN_AND_TYPE = "SELECT * FROM `buber`.`order` AS o WHERE o.`driver_login` = ? AND o.`status` = ?";

    private static final String SQL_INSERT_ORDER =
            "INSERT INTO `buber`.`order` (`client_login`, `driver_login`, `money`, `date`) VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_ORDER = "DELETE FROM `buber`.`order` WHERE `order`.`id` = ?; ";

    private static final String SQL_UPDATE_ORDER_STATUS =
            "UPDATE `buber`.`order` " +
                    "SET `client_login` = ?, `driver_login` = ?, `money` = ?, `date` = ? " +
                    "WHERE `order`.`login` = ?;";

    private DBPool dbPool = DBPool.getInstance();


    private static OrderDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

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

    private OrderDAO() {
    }

    public List<Order> find(User user, OrderType status) throws SQLException {
        List<Order> orders = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();
        if ("client".equals(user.getRole())) {
            findByRoleAndType(user, orders, connection, SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN_AND_TYPE, status);
        } else if ("driver".equals(user.getRole())) {
            findByRoleAndType(user, orders, connection, SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN_AND_TYPE, status);
        }
        return orders;
    }

    private void findByRoleAndType(User user, List<Order> orders, Connection connection, String sqlSelectAllOrdersByDriverLogin, OrderType status) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(sqlSelectAllOrdersByDriverLogin)) {
            st.setString(1, user.getLogin());
            st.setString(2, status.name());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public Order find(Order order) throws SQLException {
        dbPool = DBPool.getInstance();
        Order newOrder = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_ORDER_BY_DATE)) {
            st.setDate(1, order.getDate());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    newOrder = createOrder(resultSet);
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newOrder;
    }

    public List<Order> find(User user) throws SQLException {
        List<Order> orders = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();
        if ("client".equals(user.getRole())) {
            findByRole(user, orders, connection, SQL_SELECT_ALL_ORDERS_BY_CLIENT_LOGIN);
        } else if ("driver".equals(user.getRole())) {
            findByRole(user, orders, connection, SQL_SELECT_ALL_ORDERS_BY_DRIVER_LOGIN);
        }

        return orders;
    }

    private void findByRole(User user, List<Order> orders, Connection connection, String sqlSelectAllOrdersByDriverLogin) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(sqlSelectAllOrdersByDriverLogin)) {
            st.setString(1, user.getLogin());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public Order find(String param1, String param2) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_ORDERS)) {
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
        } finally {
            dbPool.putConnection(cn);
        }
        return orders;
    }

    @Override
    public void insert(Order order) throws SQLException {
        System.out.println(order.getStatus().name());
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_ORDER)) {
            st.setString(1, order.getClientLogin());
            st.setString(2, order.getDriverLogin());
            st.setString(3, order.getMoney());
            st.setDate(4, order.getDate());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(Order order) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            st.setInt(1, order.getId());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    public void delete(int... orders) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            for (int orderId : orders) {
                st.setInt(1, orderId);
                st.addBatch();
            }
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    public void delete(Order... orders) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            for (Order order : orders) {
                st.setInt(1, order.getId());
                st.addBatch();
            }
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_ORDER)) {
            st.setInt(1, id);
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void update(Order order) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS)) {
            st.setString(1, order.getStatus().name());
            st.setInt(2, order.getId());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }


    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setClientLogin(resultSet.getString("client_login"));
        order.setDriverLogin(resultSet.getString("driver_login"));
        order.setMoney(resultSet.getString("money"));
        order.setStatus(OrderType.valueOf(resultSet.getString("status").toUpperCase(Locale.ENGLISH)));
        order.setDate(resultSet.getDate("date"));
        return order;
    }
}




