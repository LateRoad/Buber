package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;

/**
 * An object that represent a builder for Entity objects group.
 * Provides build statement methods for each of descendants.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public interface StatementBuilder<E extends Entity> {

    /**
     * Creates a <code>Statement</code> entity from HttpServletRequest.
     * Filling in the insert statement for executing in DAO.
     * Throws a BuberSQLException if count of fields for filling in in statement is not
     * equals to count that method provides.
     *
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     */
    void makeInsertStatement(String login, E entity, PreparedStatement statement) throws BuberSQLException;

    /**
     * Creates a <code>Statement</code> entity from params.
     * Filling in the update statement for executing in DAO.
     * Throws a BuberSQLException if count of fields for filling in in statement is not
     * equals to count that method provides.
     *
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     */
    void makeUpdateStatement(String login, E entity, PreparedStatement statement) throws BuberSQLException;
}
