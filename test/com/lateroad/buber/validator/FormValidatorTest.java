package com.lateroad.buber.validator;

import com.lateroad.buber.entity.role.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormValidatorTest {

    @Test
    public void testCheckPasswordsTrue() throws Exception {
        String password1 = "431kd";
        String password2 = new String("431kd");

        boolean actual = FormValidator.checkPasswords(password1, password2);
        Assert.assertEquals(actual, true);
    }

    @Test
    public void testCheckPasswordsFalse() throws Exception {
        String password1 = "431k";
        String password2 = new String("31kd");

        boolean actual = FormValidator.checkPasswords(password1, password2);
        Assert.assertFalse(actual);
    }

    @Test
    public void testCheckNecessaryFieldsTrue() throws Exception {
        User user = new User(
                "d",
                null,
                "и",
                "4",
                null,
                "a4");
        boolean actual = FormValidator.checkNecessaryFields(user);
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckNecessaryFieldsFalse() throws Exception {
        User user = new User(
                null,
                null,
                null,
                null,
                null,
                null);
        User user1 = new User(
                "",
                null,
                "",
                "",
                "",
                "");
        boolean actual = FormValidator.checkNecessaryFields(user);
        boolean actual1 = FormValidator.checkNecessaryFields(user1);
        Assert.assertFalse(actual);
        Assert.assertFalse(actual1);
    }
}