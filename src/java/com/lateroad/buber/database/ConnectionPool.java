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

/**
 * Connection pool which supplies DAO methods with connections to database. Keep alive
 * connections in itself. Default count of connections is 15. Provides with such methods as
 * <code>getConnection()</code> and <code>putConnection</code>.
 *
 * @author LateRoad
 * @see ConnectorDB
 * @since JDK1.8
 */
public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final int DEFAULT_POOL_SIZE = 15;
    private static ConnectionPool instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE, true);

    /**
     * Private constructor for <code>ConnectionPool</code>.
     * Initialize connections for pool.
     */
    private ConnectionPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                pool.add(ConnectorDB.getConnection());
            } catch (SQLException e) {
                LOGGER.error("SQLException was occurred during pool creation.", e);
                throw new BuberFatalException(e);
            }
        }
        LOGGER.info("Connection pool was successfully initialized.");
    }

    /**
     * Returns a thread-safe singleton of the ConnectionPool.
     *
     * @return ConnectionPool instance.
     */
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

    /**
     * Returns a connection to database.
     *
     * @return connection.
     */
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

    /**
     * Put connection into connection pool and save it alive.
     */
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
