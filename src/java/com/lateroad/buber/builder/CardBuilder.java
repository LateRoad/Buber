package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Card;
import com.lateroad.buber.exception.BuberSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardBuilder implements StatementBuilder<Card> {

    @Override
    public Card build(ResultSet resultSet) throws BuberSQLException {
        Card card = null;
        try {
            card = new Card(
                    resultSet.getInt("id"),
                    resultSet.getString("hash_cardnumber"),
                    resultSet.getString("login"));
        } catch (SQLException e) {
            throw new BuberSQLException("Something went wrong.", e);
        }
        return card;
    }

    @Override
    public void makeInsertStatement(String login, Card card, PreparedStatement statement) {
        try {
            statement.setString(1, card.getHashNumber());
            statement.setString(2, card.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeUpdateStatement(String login, Card entity, PreparedStatement statement) {
        throw new UnsupportedOperationException();
    }
}
