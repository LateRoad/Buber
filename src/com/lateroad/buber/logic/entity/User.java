package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private String role;
    private boolean isMuted;


    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String role, boolean isMuted) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.isMuted = isMuted;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isMuted == user.isMuted &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, password, role, isMuted);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isMuted=" + isMuted +
                '}';
    }
}