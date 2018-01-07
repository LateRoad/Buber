package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class DriverInfo {
    private String carNumber;
    private String reputation;
    private String phoneNumber;
    private String tripsNumber;
    private String driverLicense;
    private boolean isOnline;
    private boolean isBusy;

    public DriverInfo(String carNumber, String reputation, String phoneNumber, String tripsNumber, String driverLicense, boolean isOnline, boolean isBusy) {
        this.carNumber = carNumber;
        this.reputation = reputation;
        this.phoneNumber = phoneNumber;
        this.tripsNumber = tripsNumber;
        this.driverLicense = driverLicense;
        this.isOnline = isOnline;
        this.isBusy = isBusy;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public String getTripsNumber() {
        return tripsNumber;
    }

    public void setTripsNumber(String tripsNumber) {
        this.tripsNumber = tripsNumber;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverInfo that = (DriverInfo) o;
        return isOnline == that.isOnline &&
                isBusy == that.isBusy &&
                Objects.equals(carNumber, that.carNumber) &&
                Objects.equals(reputation, that.reputation) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(tripsNumber, that.tripsNumber) &&
                Objects.equals(driverLicense, that.driverLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, reputation, phoneNumber, tripsNumber, driverLicense, isOnline, isBusy);
    }

    @Override
    public String toString() {
        return "DriverInfo{" +
                "carNumber='" + carNumber + '\'' +
                ", reputation='" + reputation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tripsNumber='" + tripsNumber + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", isOnline=" + isOnline +
                ", isBusy=" + isBusy +
                '}';
    }
}
