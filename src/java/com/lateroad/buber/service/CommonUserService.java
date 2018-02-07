package com.lateroad.buber.service;

import com.lateroad.buber.command.impl.database.dao.UserDAO;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;

public interface CommonUserService<E extends CurrentModel> {

    E authentication(String login, String password) throws BuberSQLException;


//    public User userGetByEmailAndPassword(String login, String password) throws SQLException, BuberSQLException {
//        User user = UserDAO.getInstance().find(login, password, "user");
//        setOnline(user.getLogin(), true);
//        return user;
//    }
//
//    public User
//
//    public User findUser(String login) throws SQLException, BuberSQLException {
//        User user = ClientDAO.getInstance().find(login);
//        setOnline(user.getLogin(), true);
//        return user;
//    }
//

    default void setOnline(String login, boolean status) throws BuberSQLException {
        UserDAO.getInstance().setOnline(login, status);
    }
}
