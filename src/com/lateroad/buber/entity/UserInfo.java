package com.lateroad.buber.entity;

import java.util.Objects;

public class UserInfo extends Entity{
    private String name;
    private String surname;
    private String lastname;
    private String email;
    private DriverInfo driverInfo;
    private ClientInfo clientInfo;
    private Location location;

    public UserInfo() {
        driverInfo = new DriverInfo();
        clientInfo = new ClientInfo();
    }

    public UserInfo(String name, String surname, String lastname, String email) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        driverInfo = new DriverInfo();
        clientInfo = new ClientInfo();
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

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(name, userInfo.name) &&
                Objects.equals(surname, userInfo.surname) &&
                Objects.equals(lastname, userInfo.lastname) &&
                Objects.equals(email, userInfo.email) &&
                Objects.equals(driverInfo, userInfo.driverInfo) &&
                Objects.equals(clientInfo, userInfo.clientInfo) &&
                Objects.equals(location, userInfo.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, lastname, email, driverInfo, clientInfo, location);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", driverInfo=" + driverInfo +
                ", clientInfo=" + clientInfo +
                ", location=" + location +
                '}';
    }
}
