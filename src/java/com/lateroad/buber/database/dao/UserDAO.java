package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.UserBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>User</code> objects. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see DAO
 * @since JDK1.8
 */
public class UserDAO extends CommonDAO<User> implements DAO {

    private static UserDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_INSERT_USER_INFO =
            "INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String SQL_DELETE_USER =
            "DELETE FROM `buber`.`user` WHERE `user`.`login` = ?; ";

    private static final String SQL_UPDATE_USER_INFO =
            "UPDATE `buber`.`user_info` " +
                    "SET `name` = ?, `surname` = ?, `lastname` = ?, `email` = ? " +
                    "WHERE `user_info`.`login` = ?;";

    private static final String SQL_SELECT_USER =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "WHERE u.`login` = ?;";

    /**
     * Private constructor for <code>UserDAO</code>.
     */
    private UserDAO() {
        super(new UserBuilder());
    }

    /**
     * Returns a thread-safe singleton of the UserDAO.
     *
     * @return UserDAO instance.
     */
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

    /**
     * Find the <code>User</code> using only login.
     *
     * @return <code>User</code> object.
     */
    public User find(String login) throws BuberLogicException, BuberSQLException {
        return super.find(login, SQL_SELECT_USER);
    }

    /**
     * Insert <code>User</code> object into order database.
     */
    public void insert(String login, User entity) throws BuberSQLException, BuberLogicException {
        super.insert(login, entity, SQL_INSERT_USER_INFO);
    }

    /**
     * Update <code>User</code> status in user_info database.
     */
    public void update(String login, User entity) throws BuberSQLException, BuberLogicException {
        super.update(login, entity, SQL_UPDATE_USER_INFO);
    }

    /**
     * Update <code>User</code> status in user_info database.
     */
    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_USER);
    }
}
