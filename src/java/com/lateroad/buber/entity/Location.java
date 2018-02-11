package com.lateroad.buber.entity;

import java.util.Objects;

/**
 * Class {@code Location} is an object with driver data.
 * Contains latitude and longitude of user.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public class Location implements Entity {
    private String login;
    private String lat;
    private String lng;


    /**
     * Public constructor for Location class.
     */
    public Location() {
    }

    /**
     * Public constructor for Location class.
     */
    public Location(String login, String lat, String lng) {
        this.login = login;
        this.lat = lat;
        this.lng = lng;
    }


    /**
     * Standard getter for login variable.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Standard setter for login variable.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Standard getter for lat variable.
     */
    public String getLat() {
        return lat;
    }

    /**
     * Standard setter for lat variable.
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Standard getter for lng variable.
     */
    public String getLng() {
        return lng;
    }

    /**
     * Standard setter for lng variable.
     */
    public void setLng(String lng) {
        this.lng = lng;
    }


    /**
     * Standard equals method for Location class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(login, location.login) &&
                Objects.equals(lat, location.lat) &&
                Objects.equals(lng, location.lng);
    }

    /**
     * Standard hashCode method for Location class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, lat, lng);
    }

    /**
     * Standard toString method for Location class.
     */
    @Override
    public String toString() {
        return "Location{" +
                "login='" + login + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
