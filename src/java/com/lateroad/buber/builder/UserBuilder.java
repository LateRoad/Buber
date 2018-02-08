package com.lateroad.buber.builder;

import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements StatementBuilder<User> {
    @Override
    public void makeInsertStatement(String login, User entity, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getLastname());
            statement.setString(5, entity.getEmail());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }

    @Override
    public void makeUpdateStatement(String login, User entity, PreparedStatement statement) throws BuberSQLException {

    }

    @Override
    public void makeSecurityInsertStatement(String login, String password, User entity, PreparedStatement statement) throws BuberSQLException {

    }

    @Override
    public User build(ResultSet resultSet) throws BuberSQLException {
        return null;
    }
}
