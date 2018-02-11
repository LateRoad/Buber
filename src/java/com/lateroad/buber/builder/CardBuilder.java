package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Card;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@code CardBuilder} is the builder for card entity.
 * This class allow you building card entity from <code>ResultSet</code>,
 * <code>HttpServletRequest</code> and making necessarily statements for
 * inserting and updating information in database.
 *
 * @author LateRoad
 * @see EntityBuilder
 * @since JDK1.8
 */
public class CardBuilder implements EntityBuilder<Card> {
    private static final Logger LOGGER = Logger.getLogger(CardBuilder.class);

    /**
     * Returns a Card object made from <code>ResultSet</code>. Can throw
     * <code>BuberSQLException</code> if <code>resultSet.getString()</code> get wrong
     * string as param.
     *
     * @param resultSet from statement.
     * @return <code>Card</code> object.
     * @throws BuberSQLException if <code>resultSet.getString()</code> get wrong string as param.
     * @see PreparedStatement
     */
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

    /**
     * Returns a <code>Card</code> object made from <code>HttpServletRequest</code>.
     * params for <code>Card</code> is getting from constant from constants.properties.
     *
     * @param req that represent a request for java server.
     * @return <code>Card</code> object.
     * @see HttpServletRequest
     */
    @Override
    public Card build(HttpServletRequest req) {
        return new Card(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("hash_cardnumber"),
                req.getParameter("login"));
    }

    /**
     * Filling in the insert statement for executing in DAO. Throws a BuberSQLException if
     * count of fields for filling in in statement is not equals to count that method
     * provides.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
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

    /**
     * Is not supported yet.
     *
     * @param statement is a statement for filling in.
     * @throws BuberSQLException if count of fields for filling in in statement is not
     *                           equals to count that method provides.
     * @see PreparedStatement
     */
    @Override
    public void makeUpdateStatement(String login, Card entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperationException();
    }

}
