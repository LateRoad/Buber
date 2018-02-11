package com.lateroad.buber.builder.role;

import com.lateroad.buber.builder.EntityBuilder;
import com.lateroad.buber.entity.role.CommonUser;

import javax.servlet.http.HttpServletRequest;

/**
 * An object that represent a builder for Role entities such as <code>Client</code>,
 * <code>Driver</code>, etc. Provides common methods for every entity and add
 * special methods for role.
 *
 * @author LateRoad
 * @see EntityBuilder
 * @since JDK1.8
 */
public interface RoleBuilder<E extends CommonUser> extends EntityBuilder<E> {

    /**
     * Builds a <code>CommonUser</code> entity from HttpServletRequest.
     * Returns a <code>CommonUser</code> object made from <code>HttpServletRequest</code>.
     * params for <code>CommonUser</code> is getting from constant from constants.properties.
     *
     * @return CommonUser object.
     */
    @Override
    E build(HttpServletRequest request);
}
