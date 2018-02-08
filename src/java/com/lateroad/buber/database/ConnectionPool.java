package com.lateroad.buber.database;

import com.lateroad.buber.exception.BuberFatalException;
import com.lateroad.buber.exception.BuberLogicException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final int POOL_SIZE = 15;
    private static ConnectionPool instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(POOL_SIZE, true);


    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                pool.add(ConnectorDB.getConnection());
            } catch (SQLException e) {
                LOGGER.error("SQLException was occurred during pool creation.", e);
                throw new BuberFatalException(e);
            }
        }
        LOGGER.info("Connection pool was successfully initialized.");
    }

    public Connection getConnection() throws BuberLogicException {
        Connection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException was occurred while getting connection.", e);
            throw new BuberLogicException("Something went wrong.");
        }
        return connection;
    }

    public void putConnection(Connection connection) throws BuberLogicException {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException was occurred while putting connection.", e);
                throw new BuberLogicException("Something went wrong.");
            }
        }
    }
}
