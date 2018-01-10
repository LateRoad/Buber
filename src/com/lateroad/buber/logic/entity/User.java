package com.lateroad.buber.logic.entity;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private String role;
    private UserInfo userInfo;

    public User() {
        userInfo = new UserInfo();
    }

    public User(String login, String role, UserInfo userInfo) {
        this.login = login;
        this.role = role;
        this.userInfo = userInfo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(role, user.role) &&
                Objects.equals(userInfo, user.userInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, role, userInfo);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}