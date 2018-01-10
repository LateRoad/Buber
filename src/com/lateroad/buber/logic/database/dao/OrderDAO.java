package com.lateroad.buber.logic.database.dao;

import com.lateroad.buber.logic.database.CommonDAO;
import com.lateroad.buber.logic.database.DBPool;
import com.lateroad.buber.logic.entity.Location;
import com.lateroad.buber.logic.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class OrderDAO implements CommonDAO<Order> {
    private static final String SQL_SELECT_ORDER_BY_DATE = "SELECT * FROM `buber`.`order` AS o WHERE o.`date` = ? ";

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM `buber`.`order` ";

    private static final String SQL_INSERT_ORDER =
            "INSERT INTO `buber`.`order` (`client_login`, `driver_login`, `money`, `date`) VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_ORDER = "DELETE FROM `buber`.`order` WHERE `order`.`id` = ?; ";

    private static final String SQL_UPDATE_ORDER_STATUS =
            "UPDATE `buber`.`order` " +
                    "SET `client_login` = ?, `city` = ?, `street` = ?, `house_number` = ? " +
                    "WHERE `location`.`login` = ?;";

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
            st.setBoolean(1, order.isDone());
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
        order.setDone(resultSet.getBoolean("is_done"));
        order.setDate(resultSet.getDate("date"));
        return order;
    }
}



