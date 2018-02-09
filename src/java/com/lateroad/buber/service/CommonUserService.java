package com.lateroad.buber.service;

import com.lateroad.buber.database.dao.CommonUserDAO;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

public interface CommonUserService<E extends CommonUser> {

    E authentication(String login, String password) throws BuberSQLException, BuberLogicException;

    E registration(E user, String password, String confirmPassword) throws BuberSQLException, BuberLogicException;


    default void setOnline(String login, boolean status) throws BuberSQLException, BuberLogicException {
        CommonUserDAO.getInstance().setOnline(login, status);
    }
}
