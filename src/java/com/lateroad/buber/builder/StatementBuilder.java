package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;

public interface StatementBuilder<E extends Entity> extends Builder<E> {

    void makeInsertStatement(String login, E entity, PreparedStatement statement) throws BuberSQLException;

    void makeUpdateStatement(String login, E entity, PreparedStatement statement) throws BuberSQLException;

    void makeSecurityInsertStatement(String login, String password, E entity, PreparedStatement statement) throws BuberSQLException;
}
