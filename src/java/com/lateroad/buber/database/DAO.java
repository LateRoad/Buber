package com.lateroad.buber.database;

/**
 * An object that represent a common interface for DAO. Provides connection pool for descendant.
 *
 * @author LateRoad
 * @see ConnectionPool
 * @since JDK1.8
 */
public interface DAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
}
