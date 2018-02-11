package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;

/**
 * An object that represent a builder for Entity objects group,
 * Provides common methods for every entity.
 *
 * @author LateRoad
 * @see ResultSetBuilder
 * @see RequestBuilder
 * @see StatementBuilder
 * @since JDK1.8
 */
public interface EntityBuilder<E extends Entity> extends ResultSetBuilder<E>, RequestBuilder<E>, StatementBuilder<E> {
}
