package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.type.UserType;

/**
 * Class {@code Client} is an object with client data.
 *
 * @author LateRoad
 * @see User
 * @since JDK1.8
 */
public class Client extends User {
    /**
     * Public constructor for Client class.
     */
    public Client(String login, String name, String surname, String lastname, String email, String phoneNumber, int tripsNumber, int reputation, boolean isMuted) {
        super(login, UserType.CLIENT, name, surname, lastname, email, phoneNumber, tripsNumber, reputation, isMuted);
    }

    /**
     * Public constructor for Client class.
     */
    public Client(String login, String name, String surname, String lastname, String email, String phoneNumber) {
        super(login, UserType.CLIENT, name, surname, lastname, email, phoneNumber, 0, 0, false);
    }
}
