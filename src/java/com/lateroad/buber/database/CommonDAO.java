package com.lateroad.buber.database;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberLogicException;

import java.sql.SQLException;
import java.util.List;

public interface CommonDAO<T extends Entity> {
    T find(T item) throws SQLException;

    T find(String param1, String param2) throws SQLException, BuberLogicException;

    List<T> findAll() throws SQLException;

    void insert(T item) throws SQLException;

    void delete(T item) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T item) throws SQLException;
}
