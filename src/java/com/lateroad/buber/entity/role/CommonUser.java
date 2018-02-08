package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.entity.type.UserType;

import java.util.Objects;

public class CommonUser implements Entity {
    private String login;
    private UserType role;

    public CommonUser(String login, UserType role) {
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUser that = (CommonUser) o;
        return Objects.equals(login, that.login) &&
                role == that.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, role);
    }

    @Override
    public String toString() {
        return "CommonUserBuilder{" +
                "login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}
