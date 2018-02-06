package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.type.UserType;

import java.util.Objects;

public class Driver extends User {
    private String carNumber;

    public Driver(String login, UserType role, String name, String surname, String lastname, String email, String phoneNumber, int tripsNumber, int reputation, String carNumber) {
        super(login, role, name, surname, lastname, email, phoneNumber, tripsNumber, reputation);
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(carNumber, driver.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carNumber);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "carNumber='" + carNumber + '\'' +
                '}';
    }
}
