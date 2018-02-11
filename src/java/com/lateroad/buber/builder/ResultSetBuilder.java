package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.ResultSet;

/**
 * An object that represent a builder for Entity objects group.
 * Provides build from <code>ResultSetBuilder</code> method.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public interface ResultSetBuilder<E extends Entity> {

    /**
     * Builds a <code>Entity</code> object from HttpServletRequest.
     * Returns a <code>Entity</code> object made from <code>HttpServletRequest</code>.
     * params for <code>Entity</code> is getting from constant from constants.properties.
     *
     * @return Entity object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     */
    E build(ResultSet resultSet) throws BuberSQLException;
}
