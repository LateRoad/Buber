package com.lateroad.buber.builder;

import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements StatementBuilder<User> {
    @Override
    public User build(ResultSet resultSet) throws BuberSQLException {
        CommonUser user = null;
        try {
            user = new User(
                    resultSet.getString("login"),
                    UserType.valueOf(resultSet.getString("role").toUpperCase()));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return user;
    }

    @Override
    public void makeInsertStatement(String login, User entity, PreparedStatement statement) {

    }

    @Override
    public void makeUpdateStatement(String login, User entity, PreparedStatement statement) {

    }
}
