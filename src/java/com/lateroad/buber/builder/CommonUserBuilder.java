package com.lateroad.buber.builder;

import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonUserBuilder implements StatementBuilder<CommonUser> {
    @Override
    public CommonUser build(ResultSet resultSet) throws BuberSQLException {
        CommonUser user;
        try {
            user = new CommonUser(
                    resultSet.getString("login"),
                    UserType.valueOf(resultSet.getString("role").toUpperCase()));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return user;
    }

    @Override
    public void makeInsertStatement(String login, CommonUser entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperation();
    }

    @Override
    public void makeUpdateStatement(String login, CommonUser entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperation();
    }

    @Override
    public void makeSecurityInsertStatement(String login, String password, CommonUser entity, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, entity.getRole().name());
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
    }
}
