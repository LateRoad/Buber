package com.lateroad.buber.validator;

import com.lateroad.buber.entity.role.User;

public class FormValidator {
    public static boolean checkPasswords(String password, String repeatPassword) {
        return password != null && repeatPassword != null && password.equals(repeatPassword);
    }

    public static boolean checkNecessaryFields(User user) {
        boolean status = true;
        if ("".equals(user.getLogin()) || "".equals(user.getName()) || "".equals(user.getSurname()) || "".equals(user.getEmail())) {
            status = false;
        }
        return status;
    }
}
