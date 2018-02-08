package com.lateroad.buber.service;

import com.lateroad.buber.command.impl.database.dao.UserDAO;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;

public interface CommonUserService<E extends CurrentModel> {

    E authentication(String login, String password) throws BuberSQLException;

    E registration(String... params) throws BuberSQLException;


    default void setOnline(String login, boolean status) throws BuberSQLException {
        UserDAO.getInstance().setOnline(login, status);
    }
}
