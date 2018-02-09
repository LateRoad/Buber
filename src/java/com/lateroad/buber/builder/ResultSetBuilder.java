package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.ResultSet;

public interface ResultSetBuilder<E extends Entity> {
    E build(ResultSet resultSet) throws BuberSQLException;
}
