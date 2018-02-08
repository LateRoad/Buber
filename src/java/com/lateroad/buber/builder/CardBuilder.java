package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Card;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardBuilder implements StatementBuilder<Card> {
    private static final Logger LOGGER = Logger.getLogger(CardBuilder.class);


    @Override
    public Card build(ResultSet resultSet) throws BuberSQLException {
        Card card;
        try {
            card = new Card(
                    resultSet.getInt("id"),
                    resultSet.getString("hash_cardnumber"),
                    resultSet.getString("login"));
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building a card entity.", e);
            throw new BuberSQLException("Something went wrong.");
        }
        return card;
    }

    @Override
    public void makeInsertStatement(String login, Card card, PreparedStatement statement) throws BuberSQLException {
        try {
            statement.setString(1, card.getHashNumber());
            statement.setString(2, card.getLogin());
        } catch (SQLException e) {
            LOGGER.error("SQLException was occurred building an insert statement for card.", e);
            throw new BuberSQLException("Something went wrong.");
        }
    }

    @Override
    public void makeUpdateStatement(String login, Card entity, PreparedStatement statement) {
        throw new BuberUnsupportedOperationException();
    }

    @Override
    public void makeSecurityInsertStatement(String login, String password, Card entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperationException();
    }
}
