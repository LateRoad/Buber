package com.lateroad.buber.entity;

import java.util.Objects;

public class DriverInfo extends Entity {
    private String carNumber;
    private int reputation;
    private String phoneNumber;
    private int tripsNumber;
    private String driverLicense;
    private boolean isBusy;


    public DriverInfo() {
    }

    public DriverInfo(String carNumber, int reputation, String phoneNumber, int tripsNumber, String driverLicense, boolean isBusy) {
        this.carNumber = carNumber;
        this.reputation = reputation;
        this.phoneNumber = phoneNumber;
        this.tripsNumber = tripsNumber;
        this.driverLicense = driverLicense;
        this.isBusy = isBusy;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public int getTripsNumber() {
        return tripsNumber;
    }

    public void setTripsNumber(int tripsNumber) {
        this.tripsNumber = tripsNumber;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
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
        return reputation == that.reputation &&
                tripsNumber == that.tripsNumber &&
                isBusy == that.isBusy &&
                Objects.equals(carNumber, that.carNumber) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(driverLicense, that.driverLicense);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carNumber, reputation, phoneNumber, tripsNumber, driverLicense, isBusy);
    }

    @Override
    public String toString() {
        return "DriverInfo{" +
                "carNumber='" + carNumber + '\'' +
                ", reputation=" + reputation +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tripsNumber=" + tripsNumber +
                ", driverLicense='" + driverLicense + '\'' +
                ", isBusy=" + isBusy +
                '}';
    }
}