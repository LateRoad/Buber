package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.entity.type.UserType;

import java.util.Objects;

public abstract class User extends CommonUser implements Entity {
    protected String name;
    protected String surname;
    protected String lastname;
    protected String email;
    protected String phoneNumber;
    protected int tripsNumber;
    protected int reputation;

    public User(String login, UserType role, String name, String surname, String lastname, String email, String phoneNumber, int tripsNumber, int reputation) {
        super(login, role);
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tripsNumber = tripsNumber;
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTripsNumber() {
        return tripsNumber;
    }

    public void setTripsNumber(int tripsNumber) {
        this.tripsNumber = tripsNumber;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return reputation == user.reputation &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(tripsNumber, user.tripsNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, surname, lastname, email, phoneNumber, tripsNumber, reputation);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tripsNumber='" + tripsNumber + '\'' +
                ", reputation=" + reputation +
                '}';
    }
}