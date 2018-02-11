package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.CardBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.Card;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAO Class which handling actions with <code>Card</code> object. Provides common kit of
 * command such as selecting, inserting, updating and deleting for relevant object.
 *
 * @author LateRoad
 * @see CommonDAO
 * @see DAO
 * @since JDK1.8
 */
public class CardDAO extends CommonDAO<Card> implements DAO {
    private static final Logger LOGGER = Logger.getLogger(CardDAO.class);

    private static CardDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);


    private static final String SQL_SELECT_CARD = "SELECT * FROM `buber`.`card_detail` AS c WHERE c.`login` = ? ";

    private static final String SQL_SELECT_ALL_CARDS = "SELECT * FROM `buber`.`card_detail` ";

    private static final String SQL_INSERT_CARD =
            "INSERT INTO `buber`.`card_detail` (`hash_cardnumber`, `login`) " +
                    "VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_CARD_BY_LOGIN =
            "DELETE FROM `buber`.`card` WHERE `card`.`login` = ?; ";

    private static final String SQL_DELETE_CARD_BY_ID =
            "DELETE FROM `buber`.`card` WHERE `card`.`id` = ?; ";


    /**
     * Private constructor for <code>CardDAO</code>.
     */
    private CardDAO() {
        super(new CardBuilder());
    }

    /**
     * Returns a thread-safe singleton of the CardDAO.
     *
     * @return CardDAO instance.
     */
    public static CardDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new CardDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Find the <code>Card</code> using only login.
     *
     * @return <code>Card</code> object.
     */
    public Card find(String login) throws BuberSQLException, BuberLogicException {
        return super.find(login, SQL_SELECT_CARD);
    }

    /**
     * Find all <code>Card</code> objects.
     *
     * @return <code>List of Card</code> object.
     */
    public List<Card> findAll() throws BuberSQLException, BuberLogicException {
        return super.findAll(SQL_SELECT_ALL_CARDS);
    }

    /**
     * Insert <code>Card</code> object into card database for specified user.
     */
    public void insert(String login, Card card) throws BuberSQLException, BuberLogicException {
        super.insert(login, card, SQL_INSERT_CARD);
    }

    /**
     * Is not supported yet.
     */
    public void update(Card card) {
        throw new BuberUnsupportedOperationException();
    }

    /**
     * Delete <code>Card</code> object from card database.
     */
    public void delete(String login) throws BuberSQLException, BuberLogicException {
        super.delete(login, SQL_DELETE_CARD_BY_LOGIN);
    }

    /**
     * Delete <code>Card</code> object from card database using card id.
     */
    public void delete(int id) throws BuberSQLException, BuberLogicException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_CARD_BY_ID)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred while executing query.", e);
            throw new BuberSQLException("Something went wrong.");
        } finally {
            connectionPool.putConnection(connection);
        }
    }
}




