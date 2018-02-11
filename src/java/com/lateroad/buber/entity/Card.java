package com.lateroad.buber.entity;

import java.util.Objects;

/**
 * Class {@code User} is an object with card data.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public class Card implements Entity {
    private int id;
    private String hashNumber;
    private String login;


    /**
     * Public constructor for Client class.
     */
    public Card(int id, String hashNumber, String login) {
        this.id = id;
        this.hashNumber = hashNumber;
        this.login = login;
    }


    /**
     * Standard getter for id variable.
     */
    public int getId() {
        return id;
    }

    /**
     * Standard setter for id variable.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Standard getter for hashNumber variable.
     */
    public String getHashNumber() {
        return hashNumber;
    }

    /**
     * Standard setter for hashNumber variable.
     */
    public void setHashNumber(String hashNumber) {
        this.hashNumber = hashNumber;
    }

    /**
     * Standard getter for login variable.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Standard setter for login variable.
     */
    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * Standard equals method for Card class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                Objects.equals(hashNumber, card.hashNumber) &&
                Objects.equals(login, card.login);
    }

    /**
     * Standard hashCode method for Card class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, hashNumber, login);
    }

    /**
     * Standard toString method for Card class.
     */
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", hashNumber='" + hashNumber + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
