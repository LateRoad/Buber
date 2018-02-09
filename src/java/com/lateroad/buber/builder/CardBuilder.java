package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Card;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardBuilder implements EntityBuilder<Card> {
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
    public Card build(HttpServletRequest req) {
        return new Card(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("hash_cardnumber"),
                req.getParameter("login"));
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

}
