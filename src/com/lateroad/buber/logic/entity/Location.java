package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class Location extends Entity{
    private String login;
    private String country;
    private String city;
    private String street;
    private String houseNumber;

    public Location() {

    }

    public Location(String login, String country, String city, String street, String houseNumber) {
        this.login = login;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(login, location.login) &&
                Objects.equals(country, location.country) &&
                Objects.equals(city, location.city) &&
                Objects.equals(street, location.street) &&
                Objects.equals(houseNumber, location.houseNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, country, city, street, houseNumber);
    }

    @Override
    public String toString() {
        return "Location{" +
                "login='" + login + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
