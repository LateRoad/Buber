package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.entity.User;

import java.sql.SQLException;

public interface CommonService {
    User userGetByEmailAndPassword(String login, String password) throws SQLException;
}
