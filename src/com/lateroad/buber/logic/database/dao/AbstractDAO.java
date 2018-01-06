package com.lateroad.buber.logic.database.dao;

import com.lateroad.buber.logic.database.CommonDAO;
import com.lateroad.buber.logic.entity.Entity;

public abstract class AbstractDAO<T extends Entity> implements CommonDAO<T> {
//    protected WrapperConnector connector;
//
//
//    protected void close() {
//        connector.closeConnection();
//    }
//    protected void closeStatement(Statement statement) {
//        connector.closeStatement(statement);
//    }
}