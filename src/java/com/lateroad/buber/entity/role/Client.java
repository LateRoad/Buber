package com.lateroad.buber.entity.role;

import com.lateroad.buber.entity.type.UserType;

public class Client extends User {
    public Client(String login, UserType role, String name, String surname, String lastname, String email, String phoneNumber, int tripsNumber, int reputation, boolean isMuted) {
        super(login, UserType.CLIENT, name, surname, lastname, email, phoneNumber, tripsNumber, reputation, isMuted);
    }

    public Client(String login, UserType role, String name, String surname, String lastname, String email, String phoneNumber) {
        super(login, UserType.CLIENT, name, surname, lastname, email, phoneNumber, 0, 0, false);
    }
}
