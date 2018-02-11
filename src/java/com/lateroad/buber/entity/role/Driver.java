package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.type.UserType;

import java.util.Objects;

/**
 * Class {@code Driver} is an object with driver data.
 *
 * @author LateRoad
 * @see User
 * @since JDK1.8
 */
public class Driver extends User {
    private String carNumber;


    /**
     * Public constructor for Driver class.
     */
    public Driver(String login, String name, String surname, String lastname, String email, String phoneNumber, int tripsNumber, int reputation, boolean isMuted, String carNumber) {
        super(login, UserType.DRIVER, name, surname, lastname, email, phoneNumber, tripsNumber, reputation, isMuted);
        this.carNumber = carNumber;
    }

    /**
     * Public constructor for Driver class.
     */
    public Driver(String login, String name, String surname, String lastname, String email, String phoneNumber, String carNumber) {
        super(login, UserType.DRIVER, name, surname, lastname, email, phoneNumber, 0, 0, false);
        this.carNumber = carNumber;
    }


    /**
     * Standard getter for carNumber variable.
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * Standard setter for carNumber variable.
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }


    /**
     * Standard equals method for Driver class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(carNumber, driver.carNumber);
    }

    /**
     * Standard hashcode method for Driver class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carNumber);
    }

    /**
     * Standard toString method for Driver class.
     */
    @Override
    public String toString() {
        return "Driver{" +
                "carNumber='" + carNumber + '\'' +
                '}';
    }
}
