package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class ClientInfo extends Entity {
    private int tripsNumber;
    private int reputation;
    private String phoneNumber;

    public ClientInfo() {
    }

    public ClientInfo(int tripsNumber, int reputation, String phoneNumber) {
        this.tripsNumber = tripsNumber;
        this.reputation = reputation;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return tripsNumber == that.tripsNumber &&
                reputation == that.reputation &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tripsNumber, reputation, phoneNumber);
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "tripsNumber='" + tripsNumber + '\'' +
                ", reputation='" + reputation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
