package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommonUserDAOTest {
    private CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();

    private CommonUser testCommonUser = new CommonUser(
            "test",
            UserType.DRIVER);


    @BeforeClass
    public void setUp() throws Exception {
        commonUserDAO.insert(testCommonUser.getLogin(), "password", testCommonUser.getRole());
    }

    @AfterClass
    public void dropDown() throws Exception {
        commonUserDAO.delete(testCommonUser.getLogin());
    }


    @Test
    public void testFindByLogin() throws Exception {
        CommonUser actualCommonUser = commonUserDAO.find(testCommonUser.getLogin());
        Assert.assertEquals(actualCommonUser, testCommonUser);
    }

    @Test
    public void testFindByLoginAndPassword() throws Exception {
        commonUserDAO.setOnline(testCommonUser.getLogin(),false);
        CommonUser actualCommonUser = commonUserDAO.find(testCommonUser.getLogin(), "password");
        Assert.assertEquals(actualCommonUser, testCommonUser);
    }

    @Test
    public void testFindAll() throws Exception {
        commonUserDAO.findAll();
    }

    @Test
    public void testUpdateInfo() throws Exception {
        commonUserDAO.update(testCommonUser.getLogin(), "password1");
    }


    @Test
    public void testSetOnline() throws Exception {
        commonUserDAO.setOnline(testCommonUser.getLogin(),true);
    }

    @Test
    public void testSetRole() throws Exception {
        testCommonUser.setRole(UserType.ADMIN);
        commonUserDAO.setRole(testCommonUser.getLogin(), UserType.ADMIN);
        CommonUser actualCommonUser = commonUserDAO.find(testCommonUser.getLogin());
        Assert.assertEquals(actualCommonUser, testCommonUser);
    }

    @Test
    public void testIsOnline() throws Exception {
        boolean expected = true;
        commonUserDAO.setOnline(testCommonUser.getLogin(), true);
        boolean actual = commonUserDAO.isOnline(testCommonUser.getLogin());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsExist() throws Exception {
        boolean expected = true;
        boolean actual = commonUserDAO.isExist(testCommonUser.getLogin());
        Assert.assertEquals(expected, actual);
    }

}