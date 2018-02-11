package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.entity.role.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DriverDAOTest {
    private DriverDAO driverDAO = DriverDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();

    private Driver testDriver = new Driver(
            "test",
            "name",
            "surname",
            "lastname",
            "email",
            "phone",
            "car");


    @BeforeClass
    public void setUp() throws Exception {
        commonUserDAO.insert(testDriver.getLogin(), "password", testDriver.getRole());
        userDAO.insert(testDriver.getLogin(), testDriver);
        driverDAO.insert(testDriver.getLogin(), testDriver);
    }

    @AfterClass
    public void dropDown() throws Exception {
        userDAO.delete(testDriver.getLogin());
    }


    @Test
    public void testFindByLogin() throws Exception {
        Driver actualDriver = driverDAO.find(testDriver.getLogin());
        Assert.assertEquals(actualDriver, testDriver);
    }

    @Test
    public void testFindByLoginAndPassword() throws Exception {
        Driver actualDriver = driverDAO.find(testDriver.getLogin(), "password");
        Assert.assertEquals(actualDriver, testDriver);
    }

    @Test
    public void testFindAll() throws Exception {
        driverDAO.findAll();
    }

    @Test
    public void testUpdateInfo() throws Exception {
        testDriver.setPhoneNumber("wrong");
        driverDAO.update(testDriver.getLogin(), testDriver);
        Driver actualClient = driverDAO.find(testDriver.getLogin());
        Assert.assertEquals(actualClient, testDriver);
    }

    @Test
    public void testUpdateStatus() throws Exception {
        driverDAO.update(testDriver.getLogin(), true);
    }
}