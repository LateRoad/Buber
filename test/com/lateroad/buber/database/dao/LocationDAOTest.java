package com.lateroad.buber.database.dao;

import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.CommonUserDAO;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.role.Client;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocationDAOTest {
    private LocationDAO locationDAO = LocationDAO.getInstance();
    private ClientDAO clientDAO = ClientDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();

    private Location testLocation = new Location(
            "test",
            "12.53",
            "55.23");

    private Client testClient = new Client(
            "test",
            "name",
            "surname",
            "lastname",
            "email",
            "phone");


    @BeforeClass
    public void setUp() throws Exception {
        commonUserDAO.insert(testClient.getLogin(), "password", testClient.getRole());
        userDAO.insert(testClient.getLogin(), testClient);
        clientDAO.insert(testClient.getLogin(), testClient);
        locationDAO.insert(testLocation.getLogin(), testLocation);
    }

    @AfterClass
    public void dropDown() throws Exception {
        locationDAO.delete(testLocation.getLogin());
        userDAO.delete(testClient.getLogin());
    }


    @Test
    public void testFindByLogin() throws Exception {
        Location actualLocation = locationDAO.find(testLocation.getLogin());
        Assert.assertEquals(actualLocation, testLocation);
    }


    @Test
    public void testFindAll() throws Exception {
        locationDAO.findAll();
    }


    @Test
    public void testUpdateInfo() throws Exception {
        testLocation.setLng("20.0");
        locationDAO.update(testLocation.getLogin(), testLocation);
        Location actualLocation = locationDAO.find(testLocation.getLogin());
        Assert.assertEquals(actualLocation, testLocation);
    }
}