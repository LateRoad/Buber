package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;

import java.sql.PreparedStatement;

public interface StatementBuilder<E extends Entity> extends Builder<E> {

    void makeInsertStatement(String login, E entity, PreparedStatement statement);

    void makeUpdateStatement(String login, E entity, PreparedStatement statement);
}
