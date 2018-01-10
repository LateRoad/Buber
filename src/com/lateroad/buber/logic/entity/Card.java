package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class Card extends Entity{
    private int id;
    private String hashNumber;
    private String login;

    public Card(String hashNumber, String login) {
        this.hashNumber = hashNumber;
        this.login = login;
    }

    public Card(int id, String hashNumber, String login) {
        this.id = id;
        this.hashNumber = hashNumber;
        this.login = login;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashNumber() {
        return hashNumber;
    }

    public void setHashNumber(String hashNumber) {
        this.hashNumber = hashNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                Objects.equals(hashNumber, card.hashNumber) &&
                Objects.equals(login, card.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hashNumber, login);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", hashNumber='" + hashNumber + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
