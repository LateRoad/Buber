package com.lateroad.buber.logic.database;

import com.lateroad.buber.logic.entity.Entity;

import java.util.List;

public interface CommonDAO<T extends Entity> {
    T find(T param);
    List<T> findAll();

    void insert(T entity);

    T delete(T entity);
    T delete(int id);

    T update(T entity);
}
