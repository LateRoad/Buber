package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class ClientInfo {
    private String tripsNumber;
    private String reputation;
    private String phoneNumber;
    private boolean isOnline;
    private boolean isWait;

    public ClientInfo(String tripsNumber, String reputation, String phoneNumber, boolean isOnline, boolean isWait) {
        this.tripsNumber = tripsNumber;
        this.reputation = reputation;
        this.phoneNumber = phoneNumber;
        this.isOnline = isOnline;
        this.isWait = isWait;
    }

    public String getTripsNumber() {
        return tripsNumber;
    }

    public void setTripsNumber(String tripsNumber) {
        this.tripsNumber = tripsNumber;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isWait() {
        return isWait;
    }

    public void setWait(boolean wait) {
        isWait = wait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return isOnline == that.isOnline &&
                isWait == that.isWait &&
                Objects.equals(tripsNumber, that.tripsNumber) &&
                Objects.equals(reputation, that.reputation) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tripsNumber, reputation, phoneNumber, isOnline, isWait);
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "tripsNumber='" + tripsNumber + '\'' +
                ", reputation='" + reputation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isOnline=" + isOnline +
                ", isWait=" + isWait +
                '}';
    }
}
