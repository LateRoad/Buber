package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Entity;

public interface EntityBuilder<E extends Entity> extends ResultSetBuilder<E>, RequestBuilder<E>, StatementBuilder<E> {

}
