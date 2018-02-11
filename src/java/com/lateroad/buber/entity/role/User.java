package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.entity.type.UserType;

import java.util.Objects;

/**
 * Class {@code User} is an object with user data.
 *
 * @author LateRoad
 * @see CommonUser
 * @since JDK1.8
 */
public class User extends CommonUser implements Entity {
    protected String name;
    protected String surname;
    protected String lastname;
    protected String email;
    protected String phoneNumber;
    protected int tripsNumber;
    protected int reputation;
    protected boolean isMuted;


    /**
     * Public constructor for User class.
     */
    public User(String login, UserType role, String name, String surname, String lastname, String email, String phoneNumber, int tripsNumber, int reputation, boolean isMuted) {
        super(login, role);
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tripsNumber = tripsNumber;
        this.reputation = reputation;
        this.isMuted = isMuted;
    }

    /**
     * Public constructor for User class.
     */
    public User(String login, UserType role, String name, String surname, String lastname, String email) {
        super(login, role);
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
    }


    /**
     * Standard getter for name variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Standard setter for name variable.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Standard getter for surname variable.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Standard setter for surname variable.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Standard getter for lastname variable.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Standard setter for lastname variable.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Standard getter for email variable.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Standard setter for email variable.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Standard getter for phoneNumber variable.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Standard setter for phoneNumber variable.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Standard getter for tripsNumber variable.
     */
    public int getTripsNumber() {
        return tripsNumber;
    }

    /**
     * Standard setter for tripsNumber variable.
     */
    public void setTripsNumber(int tripsNumber) {
        this.tripsNumber = tripsNumber;
    }

    /**
     * Standard getter for reputation variable.
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * Standard setter for reputation variable.
     */
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    /**
     * Standard getter for isMuted variable.
     */
    public boolean getIsMuted() {
        return isMuted;
    }

    /**
     * Standard setter for isMuted variable.
     */
    public void setIsMuted(boolean isMuted) {
        this.isMuted = isMuted;
    }


    /**
     * Standard equals method for User class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return tripsNumber == user.tripsNumber &&
                reputation == user.reputation &&
                isMuted == user.isMuted &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber);
    }

    /**
     * Standard hashCode method for User class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, lastname, email, phoneNumber, tripsNumber, reputation, isMuted);
    }

    /**
     * Standard toString method for User class.
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tripsNumber=" + tripsNumber +
                ", reputation=" + reputation +
                ", isMuted=" + isMuted +
                '}';
    }
}
