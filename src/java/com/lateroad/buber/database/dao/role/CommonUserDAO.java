package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.builder.role.CommonUserBuilder;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>CommonUser</code> object. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see RoleDAO
 * @since JDK1.8
 */
public class CommonUserDAO extends RoleDAO<CommonUser> {
    private static final Logger LOGGER = Logger.getLogger(CommonUserDAO.class);

    private static CommonUserDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_UPDATE_MUTED =
            "UPDATE `buber`.`user` " +
                    "SET user.is_muted = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_UPDATE_ONLINE =
            "UPDATE user " +
                    "SET user.is_online = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_INSERT_USER =
            "INSERT INTO `buber`.`user` (`login`, `password`, `role`) " +
                    "VALUES (?, ?, ?);";

    private static final String SQL_UPDATE_ROLE =
            "UPDATE user " +
                    "SET user.role = ? " +
                    "WHERE user.login = ? ";

    private static final String SQL_SELECT_USER_IF_ONLINE =
            "SELECT * FROM user WHERE user.login = ? AND user.is_online = 1;";

    private static final String SQL_SELECT_USER =
            "SELECT * FROM user WHERE user.login = ?";

    private static final String SQL_SELECT_ALL_USERS =
            "SELECT * FROM user";

    private static final String SQL_DELETE_USER =
            "DELETE FROM `buber`.`user` WHERE `user`.`login` = ?; ";

    private static final String SQL_UPDATE_PASSWORD =
            "UPDATE `buber`.`user` " +
                    "SET `password` = ?" +
                    "WHERE `user`.`login` = ?;";

    /**
     * Private constructor for <code>CommonUserDAO</code>.
     */
    private CommonUserDAO() {
        super(new CommonUserBuilder());
    }

    /**
     * Returns a thread-safe singleton of the CommonUserDAO.
     *
     * @return CommonUser instance.
     */
    public static CommonUserDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new CommonUserDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    /**
     * Find the <code>CommonUser</code> using login and password.
     *
     * @return <code>CommonUser</code> object.
     */
    public CommonUser find(String login, String password) throws BuberSQLException, BuberLogicException {
        return super.find(login, password, SQL_SELECT_USER);
    }

    /**
     * Find all <code>CommonUser</code> objects.
     *
     * @return <code>List of CommonUser</code> objects.
     */
    public List<CommonUser> findAll() throws BuberLogicException, BuberSQLException {
        return super.findAll(SQL_SELECT_ALL_USERS);
    }

    /**
     * Find the <code>CommonUser</code> using login.
     *
     * @return <code>CommonUser</code> object.
     */
    public CommonUser find(String login) throws BuberLogicException, BuberSQLException {
        return super.find(login, SQL_SELECT_USER);
    }

    /**
     * Insert <code>CommonUser</code> object using login, password, needed CommonUser Object and
     * query for database.
     */
    protected void insert(String login, String password, CommonUser entity, String query) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ((CommonUserBuilder) builder).makeSecurityInsertStatement(login, password, entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Insert <code>CommonUser</code> object into user database using login, password and role of
     * user.
     */
    public void insert(String login, String password, UserType role) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, role.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Update <code>CommonUser</code> password in user database.
     */
    public void update(String login, String password) throws BuberLogicException, BuberSQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Delete <code>CommonUser</code> object from user database.
     */
    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_USER);
    }

    /**
     * Update <code>CommonUser</code> object isMuted status in user database.
     */
    public void setMuted(String login, boolean isMuted) throws BuberSQLException, BuberLogicException {
        super.update(login, isMuted, SQL_UPDATE_MUTED);
    }

    /**
     * Update <code>CommonUser</code> object isOnline status in user database.
     */
    public void setOnline(String login, boolean isOnline) throws BuberSQLException, BuberLogicException {
        super.update(login, isOnline, SQL_UPDATE_ONLINE);
    }

    /**
     * Update <code>CommonUser</code> object role in user database.
     */
    public void setRole(String login, UserType role) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
            statement.setString(1, role.name());
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }

    /**
     * Check if user is online.
     */
    public boolean isOnline(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_USER_IF_ONLINE) != null;
    }

    /**
     * Check if user is exist.
     */
    public boolean isExist(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_USER) != null;
    }
}
