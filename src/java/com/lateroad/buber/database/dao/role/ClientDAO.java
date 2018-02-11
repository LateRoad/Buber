package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.builder.role.ClientBuilder;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>Client</code> object. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see RoleDAO
 * @since JDK1.8
 */
public class ClientDAO extends RoleDAO<Client> {

    private static ClientDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_SELECT_CLIENT = "  SELECT * FROM `buber`.`user` AS u " +
            "  JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`)" +
            "  JOIN `buber`.`client_info` AS ci ON (u.`login` = ci.`login`)" +
            "  WHERE u.`login` = ?";

    private static final String SQL_SELECT_ALL_CLIENTS =
            "SELECT * FROM `buber`.`user` AS u " +
                    "JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`) " +
                    "  JOIN `buber`.`location` AS loc ON (u.`login` = loc.`login`)" +
                    "JOIN `buber`.`client_info` AS ci ON (u.`login` = ci.`login`) ";

    private static final String SQL_INSERT_CLIENT_INFO =
            "INSERT INTO buber.client_info (login, phone_number) " +
                    "VALUES (?, ?)";

    private static final String SQL_DELETE_CLIENT =
            "DELETE FROM `buber`.`client_info` WHERE `client_info`.`login` = ?; ";

    private static final String SQL_UPDATE_CLIENT_STATUS =
            "UPDATE `buber`.`client_info` " +
                    "SET `is_wait` = ? " +
                    "WHERE `client_info`.`login` = ?;";

    private static final String SQL_UPDATE_CLIENT_INFO =
            "UPDATE `buber`.`client_info` " +
                    "SET `trips_number` = ?, `reputation` = ?, `phone_number` = ? " +
                    "WHERE `client_info`.`login` = ?;";


    /**
     * Private constructor for <code>ClientDAO</code>.
     */
    private ClientDAO() {
        super(new ClientBuilder());
    }

    /**
     * Returns a thread-safe singleton of the ClientDAO.
     *
     * @return ClientDAO instance.
     */
    public static ClientDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ClientDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    /**
     * Find the <code>Client</code> using login and password.
     *
     * @return <code>Client</code> object.
     */
    public Client find(String login, String password) throws BuberSQLException, BuberLogicException {
        return super.find(login, password, SQL_SELECT_CLIENT);
    }

    /**
     * Find the <code>Client</code> using only login.
     *
     * @return <code>Client</code> object.
     */
    public Client find(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_CLIENT);
    }

    /**
     * Find all <code>Client</code> objects.
     *
     * @return <code>List of Client</code> object.
     */
    public List<Client> findAll() throws BuberSQLException, BuberLogicException {
        return super.findAll(SQL_SELECT_ALL_CLIENTS);
    }

    /**
     * Insert <code>Client</code> object into client_info database.
     */
    public void insert(String login, Client client) throws BuberSQLException, BuberLogicException {
        super.insert(login, client, SQL_INSERT_CLIENT_INFO);
    }

    /**
     * Update <code>Client</code> info in client_info database.
     */
    public void update(String login, Client client) throws BuberSQLException, BuberLogicException {
        super.update(login, client, SQL_UPDATE_CLIENT_INFO);
    }

    /**
     * Update <code>Client</code> status in client_info database.
     */
    public void update(String login, boolean isWait) throws BuberSQLException, BuberLogicException {
        super.update(login, isWait, SQL_UPDATE_CLIENT_STATUS);
    }

    /**
     * Delete <code>Client</code> object from client_info database.
     */
    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_CLIENT);
    }
}
