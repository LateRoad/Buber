package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.UserBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class UserDAO extends CommonDAO<User> implements DAO {

    private static UserDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_INSERT_USER_INFO =
            "INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`) " +
                    "VALUES (?, ?, ?, ?, ?);";


    public static UserDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new UserDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private UserDAO() {
        super(new UserBuilder());
    }


    public void insert(String login, User entity) throws BuberSQLException, BuberLogicException {
        super.insert(login, entity, SQL_INSERT_USER_INFO);
    }

    public void update(String login, User entity) throws BuberSQLException, BuberLogicException {
        super.insert(login, entity, SQL_INSERT_USER_INFO);
    }
}
