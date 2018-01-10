package com.lateroad.buber.logic.database;

import com.lateroad.buber.logic.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface CommonDAO<T extends Entity> {
    T find(T item) throws SQLException;

    List<T> findAll() throws SQLException;

    void insert(T item) throws SQLException;

    void delete(T item) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T item) throws SQLException;
}
