package com.lateroad.buber.database.dao;

import com.lateroad.buber.database.dao.role.CommonUserDAO;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.UserType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserDAOTest {
    private UserDAO userDAO = UserDAO.getInstance();
    private CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();

    private User testUser = new User(
            "test",
            UserType.CLIENT,
            "name",
            "surname",
            "lastname",
            "email");


    @BeforeClass
    public void setUp() throws Exception {
        commonUserDAO.insert(testUser.getLogin(), "password", testUser.getRole());
        userDAO.insert(testUser.getLogin(), testUser);
    }

    @AfterClass
    public void dropDown() throws Exception {
        commonUserDAO.delete(testUser.getLogin());
    }



    @Test
    public void testUpdateInfo() throws Exception {
        testUser.setSurname("wrong");
        userDAO.update(testUser.getLogin(), testUser);
        User actualUser = userDAO.find(testUser.getLogin());
        Assert.assertEquals(actualUser, testUser);
    }
}