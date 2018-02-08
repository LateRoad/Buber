package com.lateroad.buber.entity;

import java.util.Objects;

public class Location implements Entity {
    private String login;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String lat;
    private String lng;


    public Location(String login, String country, String city, String street, String houseNumber, String lat, String lng) {
        this.login = login;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.lat = lat;
        this.lng = lng;
    }

    public Location() {

    }

    public Location(String login, String lat, String lng) {
        this.login = login;
        this.lat = lat;
        this.lng = lng;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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
                Objects.equals(houseNumber, location.houseNumber) &&
                Objects.equals(lat, location.lat) &&
                Objects.equals(lng, location.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, country, city, street, houseNumber, lat, lng);
    }

    @Override
    public String toString() {
        return "Location{" +
                "login='" + login + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
