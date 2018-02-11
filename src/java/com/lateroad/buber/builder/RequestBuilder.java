package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;

import javax.servlet.http.HttpServletRequest;

/**
 * An object that represent a builder for Entity objects group.
 * Provides build from <code>HttpServletRequest</code> method.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public interface RequestBuilder<E extends Entity> {

    /**
     * Builds a <code>Entity</code> object from HttpServletRequest.
     * Returns a <code>Entity</code> object made from <code>HttpServletRequest</code>.
     * params for <code>Entity</code> is getting from constant from constants.properties.
     *
     * @return Entity object.
     */
    E build(HttpServletRequest request);
}
