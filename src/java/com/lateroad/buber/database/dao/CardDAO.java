package com.lateroad.buber.database.dao;

import com.lateroad.buber.builder.CardBuilder;
import com.lateroad.buber.database.DAO;
import com.lateroad.buber.entity.Card;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CardDAO extends CommonDAO<Card> implements DAO {
    private static final String SQL_SELECT_CARD = "SELECT * FROM `buber`.`card_detail` AS c WHERE c.`login` = ? ";

    private static final String SQL_SELECT_ALL_CARDS = "SELECT * FROM `buber`.`card_detail` ";

    private static final String SQL_INSERT_CARD =
            "INSERT INTO `buber`.`card_detail` (`hash_cardnumber`, `login`) " +
                    "VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_CARD_BY_LOGIN =
            "DELETE FROM `buber`.`card` WHERE `card`.`login` = ?; ";

    private static final String SQL_DELETE_CARD_BY_ID =
            "DELETE FROM `buber`.`card` WHERE `card`.`id` = ?; ";


    private static CardDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

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

    private CardDAO() {
        super(new CardBuilder());
    }


    public Card find(String login) throws BuberSQLException {
        return super.find(login, SQL_SELECT_CARD);
    }


    public List<Card> findAll() throws BuberSQLException {
        return super.findAll(SQL_SELECT_ALL_CARDS);
    }


    public void insert(String login, Card card) throws BuberSQLException {
        super.insert(login, card, SQL_INSERT_CARD);
    }


    public void delete(String login) throws BuberSQLException {
        super.delete(login, SQL_DELETE_CARD_BY_LOGIN);
    }

    public void update(Card card) {
        throw new UnsupportedOperationException();
    }

    public void delete(int id) throws SQLException {
        Connection connection = dbPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_CARD_BY_ID)) {
            st.setInt(1, id);
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }
}




