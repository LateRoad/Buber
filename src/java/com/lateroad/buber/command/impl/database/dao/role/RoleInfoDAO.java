package com.lateroad.buber.command.impl.database.dao.role;

import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.List;

public interface RoleInfoDAO<E extends CommonUser> {
    E find(String login, String password) throws BuberSQLException;

    E find(String login) throws BuberSQLException;

    List<E> findAll() throws BuberSQLException;

    void insert(String login, E roleInfo) throws BuberSQLException;

    void delete(String login) throws BuberSQLException;

    void update(String login, E roleInfo) throws BuberSQLException;

    void update(String login, boolean isOnline) throws BuberSQLException;
}
