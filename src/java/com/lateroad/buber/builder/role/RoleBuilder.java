package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.EntityBuilder;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;

public interface RoleBuilder<E extends CommonUser> extends EntityBuilder<E> {
    void makeSecurityInsertStatement(String login, String password, E entity, PreparedStatement statement) throws BuberSQLException;
}
