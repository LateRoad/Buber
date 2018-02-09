package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberSQLException;

import javax.servlet.http.HttpServletRequest;

public interface RequestBuilder<E extends Entity> {
    E build(HttpServletRequest request) throws BuberSQLException;
}
