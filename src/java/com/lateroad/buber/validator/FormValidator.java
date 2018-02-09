package com.lateroad.buber.validator;

import com.lateroad.buber.entity.role.User;

public class FormValidator {
    public static boolean checkPasswords(String password, String repeatPassword) {
        return password != null && repeatPassword != null && password.equals(repeatPassword);
    }

    public static boolean checkNessesaryFields(User user) {
        boolean status = true;
        if (user.getLogin() == null || user.getName() == null || user.getSurname() == null || user.getEmail() == null) {
            status = false;
        }
        return status;
    }
}
