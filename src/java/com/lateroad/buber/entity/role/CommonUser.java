package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.Entity;
import com.lateroad.buber.entity.type.UserType;

import java.util.Objects;

/**
 * Class {@code CommonUser} is the root of the role class hierarchy.
 * It contain common information about every user in system.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public class CommonUser implements Entity {
    private String login;
    private UserType role;


    /**
     * Public constructor for Client class.
     */
    public CommonUser(String login, UserType role) {
        this.login = login;
        this.role = role;
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
     * Standard getter for role variable.
     */
    public UserType getRole() {
        return role;
    }

    /**
     * Standard setter for role variable.
     */
    public void setRole(UserType role) {
        this.role = role;
    }


    /**
     * Standard equals method for CommonUser class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUser that = (CommonUser) o;
        return Objects.equals(login, that.login) &&
                role == that.role;
    }

    /**
     * Standard hashcode method for CommonUser class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, role);
    }

    /**
     * Standard toString method for CommonUser class.
     */
    @Override
    public String toString() {
        return "CommonUserBuilder{" +
                "login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}
