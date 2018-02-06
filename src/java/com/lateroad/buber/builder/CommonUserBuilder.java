package com.lateroad.buber.builder;

import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonUserBuilder implements StatementBuilder<CommonUser> {
    @Override
    public CommonUser build(ResultSet resultSet) throws BuberSQLException {
        CommonUser commonUser = null;
        try {
            commonUser = new CommonUser(
                    resultSet.getString("login"),
                    UserType.valueOf(resultSet.getString("role").toUpperCase()));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return commonUser;
    }

    @Override
    public void makeInsertStatement(String login, CommonUser entity, PreparedStatement statement) {

    }

    @Override
    public void makeUpdateStatement(String login, CommonUser entity, PreparedStatement statement) {

    }
}
