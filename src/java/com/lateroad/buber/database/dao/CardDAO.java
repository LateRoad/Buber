package com.lateroad.buber.database.dao;

import com.lateroad.buber.database.CommonDAO;
import com.lateroad.buber.database.DBPool;
import com.lateroad.buber.entity.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CardDAO implements CommonDAO<Card> {
    private static final String SQL_SELECT_CARD = "SELECT * FROM `buber`.`card_detail` AS c WHERE c.`login` = ? ";

    private static final String SQL_SELECT_ALL_CARDS = "SELECT * FROM `buber`.`card_detail` ";

    private static final String SQL_INSERT_CARD =
            "INSERT INTO `buber`.`card_detail` (`hash_cardnumber`, `login`) " +
                    "VALUES (?, ?, ?, ?); ";

    private static final String SQL_DELETE_CARD_BY_LOGIN =
            "DELETE FROM `buber`.`card` WHERE `card`.`login` = ?; ";

    private static final String SQL_DELETE_CARD_BY_ID =
            "DELETE FROM `buber`.`card` WHERE `card`.`id` = ?; ";

    private DBPool dbPool = DBPool.getInstance();


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
    }

    @Override
    public Card find(Card card) throws SQLException {
        dbPool = DBPool.getInstance();
        Card newCard = null;
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_CARD)) {
            st.setString(1, card.getLogin());
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    newCard = createCard(resultSet);
                }
            }
        } finally {
            dbPool.putConnection(connection);
        }
        return newCard;
    }

    @Override
    public Card find(String param1, String param2) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Card> findAll() throws SQLException {
        List<Card> cards = new ArrayList<>();
        dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_CARDS)) {
            while (resultSet.next()) {
                cards.add(createCard(resultSet));
            }
        } finally {
            dbPool.putConnection(cn);
        }
        return cards;
    }

    @Override
    public void insert(Card card) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_CARD)) {
            st.setString(1, card.getHashNumber());
            st.setString(2, card.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(Card card) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_CARD_BY_LOGIN)) {
            st.setString(1, card.getLogin());
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        dbPool = DBPool.getInstance();
        Connection connection = dbPool.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SQL_DELETE_CARD_BY_ID)) {
            st.setInt(1, id);
            st.executeUpdate();
        } finally {
            dbPool.putConnection(connection);
        }
    }

    @Override
    public void update(Card card) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private Card createCard(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getInt("id"));
        card.setHashNumber(resultSet.getString("hash_number"));
        card.setLogin(resultSet.getString("login"));
        return card;
    }
}




