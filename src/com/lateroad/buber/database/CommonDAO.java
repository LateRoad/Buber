package com.lateroad.buber.database;

import com.lateroad.buber.entity.Entity;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CommonDAO<T extends Entity> {
    T find(T item) throws SQLException, IOException, JSONException;

    T find(String param1, String param2) throws SQLException, IOException, JSONException;

    List<T> findAll() throws SQLException, IOException, JSONException;

    void insert(T item) throws SQLException;

    void delete(T item) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T item) throws SQLException;
}
