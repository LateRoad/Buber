package com.lateroad.buber.validator;

import com.lateroad.buber.entity.role.User;

/**
 * Class provides validation of existence necessary data in entities for
 * continuing action.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class FormValidator {

    private FormValidator() {
    }

    /**
     * Comparing two passwords.
     *
     * @return <code>boolean</code>.
     */
    public static boolean checkPasswords(String password, String repeatPassword) {
        return password != null && repeatPassword != null && password.equals(repeatPassword);
    }

    /**
     * Checking necessary fields before continuing operation.
     *
     * @return <code>boolean</code>.
     */
    public static boolean checkNecessaryFields(User user) {
        return (user.getLogin() != null && user.getLogin().matches("\\p{ASCII}+") ||
                user.getName() != null && user.getName().matches("\\p{ASCII}+") ||
                user.getSurname() != null && user.getSurname().matches("\\p{ASCII}+") ||
                user.getEmail() != null && user.getEmail().matches("\\p{ASCII}+"));
    }
}
